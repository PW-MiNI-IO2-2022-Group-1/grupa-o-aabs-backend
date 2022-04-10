package com.example.SOPSbackend.repository;

import com.example.SOPSbackend.model.VaccinationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccinationRepository extends JpaRepository<VaccinationEntity, Long> {
}
