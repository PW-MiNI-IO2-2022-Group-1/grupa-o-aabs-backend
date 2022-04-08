package com.example.SOPSbackend.repository;

import com.example.SOPSbackend.model.VaccinationSlotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VaccinationSlotRepository extends JpaRepository<VaccinationSlotEntity, Long>, JpaSpecificationExecutor<VaccinationSlotEntity> { }

