package com.example.SOPSbackend.repository;

import com.example.SOPSbackend.model.VaccinationEntity;
import com.example.SOPSbackend.model.VaccinationSlotEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class VaccinationRepositoryTest {

    @Autowired
    private VaccinationRepository underTest;
    @Autowired
    private VaccinationSlotRepository vaccinationSlotRepository;

    @Test
    void findMatchingVaccinations() {
        //given
        Long ids[] = {1l, 2l, 10l};
        List<VaccinationSlotEntity> vaccinationSlotEntityList =
                vaccinationSlotRepository.findAllById(Arrays.asList(ids));

        //when
        List<VaccinationEntity> result =
                underTest.findMatchingVaccinations(vaccinationSlotEntityList);

        //then
        assertEquals(2, result.size());
    }
}