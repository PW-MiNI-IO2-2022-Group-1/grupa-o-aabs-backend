package com.example.SOPSbackend.repository;

import com.example.SOPSbackend.model.VaccinationSlotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VaccinationSlotRepository extends JpaRepository<VaccinationSlotEntity, Long> {

    @Query("select s from VaccinationSlotEntity s " +
            "left join VaccinationEntity v on s.id = v.vaccinationSlot " +
            "WHERE v.vaccinationSlot is null")
    public List<VaccinationSlotEntity> findAvailableSlots();
}
