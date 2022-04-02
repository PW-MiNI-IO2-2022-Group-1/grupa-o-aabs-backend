package com.example.SOPSbackend.service;

import com.example.SOPSbackend.exception.InternalValidationException;
import com.example.SOPSbackend.model.Doctor;
import com.example.SOPSbackend.repository.DoctorRepository;
import com.example.SOPSbackend.repository.VaccinationSlotRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.argThat;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@MockBean(DoctorRepository.class)
@MockBean(VaccinationSlotRepository.class)
class DoctorServiceTest {
    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    VaccinationSlotRepository slotRepository;

    @Autowired
    DoctorService doctorService;

    Doctor doctor;

    @Before
    public void setUp() {
        doctor = new Doctor();
    }

    @Test
    public void addVaccinationSlot_shouldThrowValidationException_whenDateIsInPast() {
        Instant date = Instant.now().minus(Duration.ofDays(1));
        assertThrows(InternalValidationException.class,
                () -> doctorService.addVaccinationSlot(doctor, date));
    }

    @Test
    public void addVaccinationSlot_shouldThrowValidationException_whenDateIsComingTooSoon() {
        Instant date = Instant.now().plus(Duration.ofMinutes(2));
        assertThrows(InternalValidationException.class,
                () -> doctorService.addVaccinationSlot(doctor, date));
    }

    @Test
    public void addVaccinationSlot_shouldSaveSlotWithTruncatedDate_whenDateIsCorrect() {
        Instant date = Instant.now().plus(Duration.ofDays(1));
        Instant truncatedDate = date.truncatedTo(ChronoUnit.MINUTES);

        LocalDateTime localDate = LocalDateTime.ofInstant(truncatedDate, ZoneId.of("UTC"));
        doctorService.addVaccinationSlot(doctor, date);

        Mockito.verify(slotRepository)
            .save(argThat(argument -> argument.getDate().equals(localDate)));
    }

}