package com.example.SOPSbackend.repository;

import com.example.SOPSbackend.model.VaccinationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VaccinationRepository extends JpaRepository<VaccinationEntity, Long>, JpaSpecificationExecutor<VaccinationEntity> {
}


