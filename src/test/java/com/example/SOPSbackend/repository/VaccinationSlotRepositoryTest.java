package com.example.SOPSbackend.repository;

import com.example.SOPSbackend.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class VaccinationSlotRepositoryTest {

    @Autowired
    private VaccinationSlotRepository underTest;
    @Autowired
    private DoctorRepository doctorRepository;

    @Test
    void shouldFindAvailableSlots() {
        //given

        //when
        List<VaccinationSlotEntity> result = underTest.findAvailableSlots();

        //then
        assertEquals(5, result.size());
    }

    @Test
    void findNotReserved() {
        //given
        Optional<DoctorEntity> doctor = doctorRepository.findById((long) 1);
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now().plusDays(40);
        Pageable page0 = PageRequest.of(0, 2, Sort.by("date"));
        Pageable page1 = PageRequest.of(1, 2, Sort.by("date"));

        //when
        Page<VaccinationSlotEntity> resultPage0 = underTest.findNotReserved(
                doctor.get(), startDate, endDate, page0);
        Page<VaccinationSlotEntity> resultPage1 = underTest.findNotReserved(
                doctor.get(), startDate, endDate, page1);

        //then
        assertEquals(2, resultPage0.getNumberOfElements());
        assertEquals(3, resultPage0.getTotalElements());
        assertEquals(3,
                resultPage0.get().filter(
                        vaccinationSlot -> vaccinationSlot.getDoctor().getId() == 1).count()
                        + resultPage1.get().filter(
                        vaccinationSlot -> vaccinationSlot.getDoctor().getId() == 1).count());
    }

    @Test
    void findReserved() {
        Optional<DoctorEntity> doctor = doctorRepository.findById((long) 1);
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now().plusDays(40);
        Pageable page0 = PageRequest.of(0, 2, Sort.by("date"));
        Pageable page1 = PageRequest.of(1, 2, Sort.by("date"));

        //when
        Page<VaccinationSlotEntity> resultPage0 = underTest.findReserved(
                doctor.get(), startDate, endDate, page0);
        Page<VaccinationSlotEntity> resultPage1 = underTest.findReserved(
                doctor.get(), startDate, endDate, page1);

        //then
        assertEquals(2, resultPage0.getNumberOfElements());
        assertEquals(2, resultPage0.getTotalElements());
        assertEquals(2,
                resultPage0.get().filter(
                        vaccinationSlot -> vaccinationSlot.getDoctor().getId() == 1).count()
                        + resultPage1.get().filter(
                        vaccinationSlot -> vaccinationSlot.getDoctor().getId() == 1).count());
    }
}