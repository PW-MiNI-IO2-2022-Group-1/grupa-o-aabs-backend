package com.example.SOPSbackend.service;

import com.example.SOPSbackend.exception.InternalValidationException;
import com.example.SOPSbackend.model.DoctorEntity;
import com.example.SOPSbackend.model.VaccinationEntity;
import com.example.SOPSbackend.model.VaccinationSlotEntity;
import com.example.SOPSbackend.repository.DoctorRepository;
import com.example.SOPSbackend.repository.VaccinationRepository;
import com.example.SOPSbackend.repository.VaccinationSlotRepository;
import com.example.SOPSbackend.security.TokenService;
import org.apache.tomcat.websocket.AuthenticationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DoctorServiceTest {

    DoctorService underTest;
    @Mock
    DoctorRepository doctorRepository;
    @Mock
    VaccinationSlotRepository vaccinationSlotRepository;
    @Mock
    VaccinationRepository vaccinationRepository;
    @Mock
    TokenService tokenService;
    @Mock
    PasswordEncoder passwordEncoder;

    DoctorEntity doctor;

    @BeforeEach
    public void setUp() {
        underTest = new DoctorService(doctorRepository, vaccinationSlotRepository, vaccinationRepository, passwordEncoder, tokenService);
        doctor = new DoctorEntity();
        doctor.setId(3l);
    }

    @Nested
    class addVaccinationSlot {

        Instant date;
        LocalDateTime dateTime;
        @Captor
        ArgumentCaptor<VaccinationSlotEntity> vaccinationSlotArgumentCaptor;

        @Test
        void shouldCallSaveWithDataPassedInParameter() {
            date = Instant.now().plusSeconds(1000);
            dateTime = LocalDateTime.ofInstant(date.truncatedTo(ChronoUnit.MINUTES), ZoneId.of("UTC"));

            underTest.addVaccinationSlot(doctor, date);

            verify(vaccinationSlotRepository).save(vaccinationSlotArgumentCaptor.capture());
            assertThat(vaccinationSlotArgumentCaptor.getValue().getDoctor()).usingRecursiveComparison()
                    .isEqualTo(doctor);
            assertThat(vaccinationSlotArgumentCaptor.getValue().getDate()).isEqualTo(dateTime);
        }

        @Test
        void whenDateInPastIsPassed_shouldThrowInternalValidationException() {
            date = Instant.now().minusSeconds(1000);
            dateTime = LocalDateTime.ofInstant(date.truncatedTo(ChronoUnit.MINUTES), ZoneId.of("UTC"));
            assertThatThrownBy(() -> underTest.addVaccinationSlot(doctor, date))
                    .isInstanceOf(InternalValidationException.class);
        }

        @Test
        void whenDuplicatedDateIsPassed_shouldThrowInternalValidationException() {
            date = Instant.now().plusSeconds(1000);
            dateTime = LocalDateTime.ofInstant(date.truncatedTo(ChronoUnit.MINUTES), ZoneId.of("UTC"));
            Mockito.when(vaccinationSlotRepository.findResultsByDateAndDoctor(dateTime, doctor))
                    .thenReturn(Arrays.asList(new VaccinationSlotEntity()));

            assertThatThrownBy(() -> underTest.addVaccinationSlot(doctor, date))
                    .isInstanceOf(InternalValidationException.class);
        }
    }

    @Captor
    ArgumentCaptor<DoctorEntity> doctorArgumentCaptor;
    @Captor
    ArgumentCaptor<Long> idArgumentCaptor;

    @Test
    void deleteVaccinationSlot_shouldDeleteByIdAndDoctorWithPassedData() {
        long vaccinationSlotId = 5;
        underTest.deleteVaccinationSlot(doctor, vaccinationSlotId);

        verify(vaccinationSlotRepository).deleteByIdAndDoctor(
                idArgumentCaptor.capture(),
                doctorArgumentCaptor.capture()
        );

        assertThat(idArgumentCaptor.getValue()).usingRecursiveComparison()
                .isEqualTo(vaccinationSlotId);
        assertThat(doctorArgumentCaptor.getValue()).isEqualTo(doctor);
    }

    @Nested
    class vaccinatePatient {
        @Captor
        ArgumentCaptor<VaccinationEntity> vaccinationArgumentCaptor;

        @Test
        void whenVaccinationSlotIsNotPresent_shouldThrowNoSuchElementException() {
            Long vaccinationSlotId = 5l;
            String status = "COMPLETED";
            Mockito.when(vaccinationSlotRepository.findById(vaccinationSlotId))
                    .thenReturn(Optional.empty());

            assertThatThrownBy(() -> underTest.vaccinatePatient(vaccinationSlotId, status, doctor))
                    .isInstanceOf(NoSuchElementException.class);
        }

        @Test
        void whenVaccinationSlotWasNotCreatedByThisDoctor_shouldThrowAuthenticationException() {
            Long vaccinationSlotId = 5l;
            String status = "COMPLETED";
            DoctorEntity fakeDoctor = new DoctorEntity();
            fakeDoctor.setId(doctor.getId() + 1);
            VaccinationSlotEntity vaccinationSlotEntity = new VaccinationSlotEntity();
            vaccinationSlotEntity.setDoctor(fakeDoctor);
            Mockito.when(vaccinationSlotRepository.findById(vaccinationSlotId))
                    .thenReturn(Optional.of(vaccinationSlotEntity));

            assertThatThrownBy(() -> underTest.vaccinatePatient(vaccinationSlotId, status, doctor))
                    .isInstanceOf(AuthenticationException.class);
        }

        @Test
        void whenVaccinationSlotIsNotReserved_shouldThrowInternalValidationException() {
            Long vaccinationSlotId = 5l;
            String status = "COMPLETED";
            VaccinationSlotEntity vaccinationSlotEntity = new VaccinationSlotEntity();
            vaccinationSlotEntity.setId(5l);
            vaccinationSlotEntity.setDoctor(doctor);
            Optional<VaccinationSlotEntity> vaccinationSlot = Optional.of(vaccinationSlotEntity);
            Mockito.when(vaccinationSlotRepository.findById(vaccinationSlotId))
                    .thenReturn(vaccinationSlot);
            Mockito.when(vaccinationRepository.findByVaccinationSlot(vaccinationSlotEntity))
                    .thenReturn(null);

            assertThatThrownBy(() -> underTest.vaccinatePatient(vaccinationSlotId, status, doctor))
                    .isInstanceOf(InternalValidationException.class);
        }

        @Test
        void whenStatusNameIsInValid_shouldThrowInternalValidationException() {
            Long vaccinationSlotId = 5l;
            String status = "NOT VALID NAME";
            VaccinationSlotEntity vaccinationSlotEntity = new VaccinationSlotEntity();
            vaccinationSlotEntity.setId(vaccinationSlotId);
            vaccinationSlotEntity.setDoctor(doctor);
            Optional<VaccinationSlotEntity> vaccinationSlot = Optional.of(vaccinationSlotEntity);
            Mockito.when(vaccinationSlotRepository.findById(vaccinationSlotId))
                    .thenReturn(vaccinationSlot);
            Mockito.when(vaccinationRepository.findByVaccinationSlot(vaccinationSlotEntity))
                    .thenReturn(new VaccinationEntity());

            assertThatThrownBy(() -> underTest.vaccinatePatient(vaccinationSlotId, status, doctor))
                    .isInstanceOf(InternalValidationException.class);
        }

        @Test
        void whenAllDataIsValid_shouldSaveVaccinationWithNewStatus() {
            Long vaccinationSlotId = 5l;
            String status = "COMPLETED";
            VaccinationSlotEntity vaccinationSlotEntity = new VaccinationSlotEntity();
            vaccinationSlotEntity.setId(vaccinationSlotId);
            vaccinationSlotEntity.setDoctor(doctor);
            Optional<VaccinationSlotEntity> vaccinationSlot = Optional.of(vaccinationSlotEntity);
            Mockito.when(vaccinationSlotRepository.findById(vaccinationSlotId))
                    .thenReturn(vaccinationSlot);
            VaccinationEntity vaccination = new VaccinationEntity();
            Long vaccinationId = 7l;
            vaccination.setId(vaccinationId);
            Mockito.when(vaccinationRepository.findByVaccinationSlot(vaccinationSlotEntity))
                    .thenReturn(vaccination);

            try {
                underTest.vaccinatePatient(vaccinationSlotId, status, doctor);
            } catch (Exception e) {
                assertThat(1).isEqualTo(2);
            }

            verify(vaccinationRepository).save(vaccinationArgumentCaptor.capture());
            assertThat(vaccinationArgumentCaptor.getValue().getStatus()).isEqualToIgnoringCase(status);
            assertThat(vaccinationArgumentCaptor.getValue().getId()).isEqualTo(vaccinationId);
        }

    }

    @Nested
    @Disabled
            //TODO
    class getVaccinationSlots {

        int page = 2;
        String startDate = "startDate";
        String endDate = "endDate";
        String onlyReserved = "onlyReserved";

        @Test
        void shouldBeGood() {
            underTest.getVaccinationSlots(doctor, page, startDate, endDate, onlyReserved);
        }
    }
}