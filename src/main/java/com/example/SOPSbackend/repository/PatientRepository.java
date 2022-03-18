package com.example.SOPSbackend.repository;

import com.example.SOPSbackend.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findPatientByEmailIgnoreCase(String email);
}
