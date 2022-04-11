package com.example.SOPSbackend.repository;

import com.example.SOPSbackend.model.DoctorEntity;
import com.example.SOPSbackend.model.VaccinationSlotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;

public interface VaccinationSlotRepository extends JpaRepository<VaccinationSlotEntity, Long>, JpaSpecificationExecutor<VaccinationSlotEntity> {
    long deleteByIdAndDoctor(
            @Param("id") Long id,
            @Param("doctor")DoctorEntity doctor
            );
}

