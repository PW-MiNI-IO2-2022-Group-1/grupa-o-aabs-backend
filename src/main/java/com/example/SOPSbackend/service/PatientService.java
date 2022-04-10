package com.example.SOPSbackend.service;

import com.example.SOPSbackend.dto.NewPatientRegistrationDto;
import com.example.SOPSbackend.exception.UserAlreadyExistException;
import com.example.SOPSbackend.model.PatientEntity;
import com.example.SOPSbackend.model.VaccinationSlotEntity;
import com.example.SOPSbackend.model.VaccineEntity;
import com.example.SOPSbackend.repository.PatientRepository;
import com.example.SOPSbackend.repository.VaccinationSlotRepository;
import com.example.SOPSbackend.repository.VaccineRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final PasswordEncoder passwordEncoder;
    private final VaccineRepository vaccineRepository;
    private final VaccinationSlotRepository vaccinationSlotRepository;

    public PatientService(PatientRepository patientRepository, PasswordEncoder passwordEncoder, VaccineRepository vaccineRepository, VaccinationSlotRepository vaccinationSlotRepository) {
        this.patientRepository = patientRepository;
        this.passwordEncoder = passwordEncoder;
        this.vaccineRepository = vaccineRepository;
        this.vaccinationSlotRepository = vaccinationSlotRepository;
    }

    public PatientEntity register(NewPatientRegistrationDto registration) throws UserAlreadyExistException {
        if(checkIfUserExistByPesel(registration.getPesel()))
            throw new UserAlreadyExistException("User already exists for this pesel");
        if(checkIfUserExistByEmail(registration.getEmail()))
            throw new UserAlreadyExistException("User already exists for this email");

        return patientRepository.save(new PatientEntity(registration, passwordEncoder));
    }

    public List<VaccineEntity> getVaccines(Collection<String> diseases) {
        return vaccineRepository.findByDiseaseIn(diseases);
    }

    public List<VaccinationSlotEntity> getAvailableVaccinationSlots()
    {
        return vaccinationSlotRepository.findAll();
    }

    public void reserveVaccinationSlot(long vaccineId, long vaccinationSlotId) {

    }

    private boolean checkIfUserExistByEmail(String email) {
        return patientRepository.findByEmailIgnoreCase(email).isPresent();
    }

    private boolean checkIfUserExistByPesel(String pesel) {
        return patientRepository.findByPesel(pesel).isPresent();
    }
}
