package com.example.SOPSbackend.service;

import com.example.SOPSbackend.dto.EditPatientAccountDto;
import com.example.SOPSbackend.dto.NewPatientRegistrationDto;
import com.example.SOPSbackend.exception.UserAlreadyExistException;
import com.example.SOPSbackend.model.PatientEntity;
import com.example.SOPSbackend.repository.PatientRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final PasswordEncoder passwordEncoder;

    public PatientService(PatientRepository patientRepository, PasswordEncoder passwordEncoder) {
        this.patientRepository = patientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public PatientEntity register(NewPatientRegistrationDto registration) throws UserAlreadyExistException {
        if(checkIfUserExistByPesel(registration.getPesel()))
            throw new UserAlreadyExistException("User already exists for this pesel");
        if(checkIfUserExistByEmail(registration.getEmail()))
            throw new UserAlreadyExistException("User already exists for this email");

        return patientRepository.save(new PatientEntity(registration, passwordEncoder));
    }

    private boolean checkIfUserExistByEmail(String email) {
        return patientRepository.findByEmailIgnoreCase(email).isPresent();
    }

    private boolean checkIfUserExistByPesel(String pesel) {
        return patientRepository.findByPesel(pesel).isPresent();
    }

    public PatientEntity editAccount(PatientEntity patient, EditPatientAccountDto newData) {
        patient.update(newData, passwordEncoder);
        patientRepository.save(patient);
        return patient;
    }
}
