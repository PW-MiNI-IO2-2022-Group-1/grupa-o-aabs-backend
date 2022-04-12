package com.example.SOPSbackend.service;

import com.example.SOPSbackend.dto.EditPatientAccountDto;
import com.example.SOPSbackend.model.PatientEntity;
import com.example.SOPSbackend.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

class PatientServiceTest {
    PasswordEncoder passwordEncoder;
    PatientRepository patientRepository;
    PatientService patientService;

    @BeforeEach
    public void setUp() {
       patientRepository = Mockito.mock(PatientRepository.class);
       passwordEncoder = Mockito.mock(PasswordEncoder.class);
       patientService = new PatientService(patientRepository, passwordEncoder);
    }

    @Test
    public void editAccount_ShouldUpdateAndSavePatient() {
        PatientEntity patient = Mockito.mock(PatientEntity.class);
        EditPatientAccountDto editPatientAccountDto = new EditPatientAccountDto();
        Mockito.when(patientRepository.findById(patient.getId())).thenReturn(Optional.of(patient));
        Mockito.when(patientRepository.save(patient)).thenReturn(patient);

        patientService.editAccount(patient, editPatientAccountDto);

        Mockito.verify(patientRepository).findById(patient.getId());
        Mockito.verify(patientRepository).save(patient);
        Mockito.verify(patient).update(editPatientAccountDto, passwordEncoder);
    }
}