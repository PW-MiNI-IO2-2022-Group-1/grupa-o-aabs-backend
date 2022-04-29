package com.example.SOPSbackend.repository;

import com.example.SOPSbackend.model.PatientEntity;

import java.util.Optional;

public interface PatientRepository extends UserRepository<PatientEntity, Long> {
    Optional<PatientEntity> findByPesel(String pesel);

}
