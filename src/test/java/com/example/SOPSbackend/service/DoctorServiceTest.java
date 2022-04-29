package com.example.SOPSbackend.service;

import com.example.SOPSbackend.exception.InternalValidationException;
import com.example.SOPSbackend.model.DoctorEntity;
import com.example.SOPSbackend.model.VaccinationSlotEntity;
import com.example.SOPSbackend.repository.DoctorRepository;
import com.example.SOPSbackend.repository.VaccinationRepository;
import com.example.SOPSbackend.repository.VaccinationSlotRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.argThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class DoctorServiceTest {

    DoctorService underTest;
    @Mock
    DoctorRepository doctorRepository;
    @Mock
    VaccinationSlotRepository vaccinationSlotRepository;
    @Mock
    VaccinationRepository vaccinationRepository;

    @BeforeEach
    public void setUp() {
        underTest = new DoctorService(doctorRepository, vaccinationSlotRepository, vaccinationRepository);
    }

    @Nested
    class addVaccinationSlot {

        DoctorEntity doctor;
        Instant date;
        @Captor
        ArgumentCaptor<VaccinationSlotEntity> vaccinationSlotArgumentCaptor;

        @BeforeEach
        void setUp() {
            doctor = new DoctorEntity();
            doctor.setId(3l);
            date = Instant.now();

            underTest.addVaccinationSlot(doctor, date);
        }

        @Test
        void shouldCallSaveWithDataPassedInParameter() {
            Mockito.verify(vaccinationSlotRepository).save(vaccinationSlotArgumentCaptor.capture());

            assertThat(vaccinationSlotArgumentCaptor.getValue().getDoctor()).isEqualTo(doctor);
            assertThat(vaccinationSlotArgumentCaptor.getValue().getDate()).isEqualTo(date);
        }
    }


//    @Autowired
//    VaccinationSlotRepository slotRepository;
//
//
//    @Test
//    public void addVaccinationSlot_shouldThrowValidationException_whenDateIsInPast() {
//        Instant date = Instant.now().minus(Duration.ofDays(1));
//        assertThrows(InternalValidationException.class,
//                () -> underTest.addVaccinationSlot(doctor, date));
//    }
//
//    /*@Test
//    public void addVaccinationSlot_shouldThrowValidationException_whenDateIsComingTooSoon() {
//        Instant date = Instant.now().plus(Duration.ofMinutes(2));
//        assertThrows(InternalValidationException.class,
//                () -> doctorService.addVaccinationSlot(doctor, date));
//    }*/
//
//    @Test
//    public void addVaccinationSlot_shouldSaveSlotWithTruncatedDate_whenDateIsCorrect() {
//        Instant date = Instant.now().plus(Duration.ofDays(1));
//        Instant truncatedDate = date.truncatedTo(ChronoUnit.MINUTES);
//
//        LocalDateTime localDate = LocalDateTime.ofInstant(truncatedDate, ZoneId.of("UTC"));
//        underTest.addVaccinationSlot(doctor, date);
//
//        Mockito.verify(slotRepository)
//                .save(argThat(argument -> argument.getDate().equals(localDate)));
//    }

}