package com.example.SOPSbackend.service;

import com.example.SOPSbackend.exception.InternalValidationException;
import com.example.SOPSbackend.model.DoctorEntity;
import com.example.SOPSbackend.model.VaccinationSlotEntity;
import com.example.SOPSbackend.repository.DoctorRepository;
import com.example.SOPSbackend.repository.VaccinationRepository;
import com.example.SOPSbackend.repository.VaccinationSlotRepository;
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

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

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
    DoctorEntity doctor;

    @BeforeEach
    public void setUp() {
        underTest = new DoctorService(doctorRepository, vaccinationSlotRepository, vaccinationRepository);
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