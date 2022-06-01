package com.example.SOPSbackend.service;

import com.example.SOPSbackend.dto.EditPatientAccountDto;
import com.example.SOPSbackend.dto.NewPatientRegistrationDto;
import com.example.SOPSbackend.exception.AlreadyReservedException;
import com.example.SOPSbackend.exception.UserAlreadyExistException;
import com.example.SOPSbackend.model.PatientEntity;
import com.example.SOPSbackend.model.VaccinationEntity;
import com.example.SOPSbackend.model.VaccinationSlotEntity;
import com.example.SOPSbackend.model.VaccineEntity;
import com.example.SOPSbackend.repository.PatientRepository;
import com.example.SOPSbackend.repository.VaccinationRepository;
import com.example.SOPSbackend.repository.VaccinationSlotRepository;
import com.example.SOPSbackend.repository.VaccineRepository;
import com.example.SOPSbackend.security.AuthorizationResult;
import com.example.SOPSbackend.security.Credentials;
import com.example.SOPSbackend.security.Role;
import com.example.SOPSbackend.security.TokenService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final PasswordEncoder passwordEncoder;
    private final VaccineRepository vaccineRepository;
    private final VaccinationSlotRepository vaccinationSlotRepository;
    private final VaccinationRepository vaccinationRepository;
    private final TokenService tokenService;
    private static final int ITEMS_PER_PAGE = 10;

    public PatientService(PatientRepository patientRepository, PasswordEncoder passwordEncoder,
                          VaccineRepository vaccineRepository, VaccinationSlotRepository vaccinationSlotRepository,
                          VaccinationRepository vaccinationRepository, TokenService tokenService) {
        this.patientRepository = patientRepository;
        this.passwordEncoder = passwordEncoder;
        this.vaccineRepository = vaccineRepository;
        this.vaccinationSlotRepository = vaccinationSlotRepository;
        this.vaccinationRepository = vaccinationRepository;
        this.tokenService = tokenService;
    }

    public AuthorizationResult logIn(Credentials credentials) {
        PatientEntity patient = patientRepository.findByEmailIgnoreCase(credentials.getEmail()).orElse(null);
        if(patient == null || !passwordEncoder.matches(credentials.getPassword(), patient.getPassword()))
            return AuthorizationResult.failedAuthorization();

        String token = tokenService.getToken(Role.PATIENT, patient);
        return AuthorizationResult.successfulAuthorization(token, patient);
    }

    public PatientEntity register(NewPatientRegistrationDto registration) throws UserAlreadyExistException {
        if (checkIfUserExistByPesel(registration.getPesel()))
            throw new UserAlreadyExistException("User already exists for this pesel");
        if (checkIfUserExistByEmail(registration.getEmail()))
            throw new UserAlreadyExistException("User already exists for this email");

        return patientRepository.save(new PatientEntity(registration, passwordEncoder));
    }

    public List<VaccineEntity> getVaccines(Collection<String> diseases) {
        return vaccineRepository.findByDiseaseIn(diseases);
    }

    public List<VaccinationSlotEntity> getAvailableVaccinationSlots() {
        return vaccinationSlotRepository.findAvailableSlots();
    }

    public void reserveVaccinationSlot(long vaccineId, long vaccinationSlotId, PatientEntity patient) throws AlreadyReservedException {
        Optional<VaccineEntity> vaccine = vaccineRepository.findById(vaccineId);
        Optional<VaccinationSlotEntity> vaccinationSlot = vaccinationSlotRepository.findById(vaccinationSlotId);

        if (vaccine.isEmpty()) {
            throw new NoSuchElementException("Vaccine not found");
        }

        if (vaccinationSlot.isEmpty()) {
            throw new NoSuchElementException("Vaccination slot not found");
        }

        VaccinationEntity vaccination = vaccinationRepository.findByVaccinationSlot(vaccinationSlot.get());

        if (vaccination != null) {
            throw new AlreadyReservedException("Vaccination slot is already reserved");
        }

        vaccinationRepository.save(new VaccinationEntity(
                patient,
                vaccine.get(),
                vaccinationSlot.get()
        ));
    }

    private boolean checkIfUserExistByEmail(String email) {
        return patientRepository.findByEmailIgnoreCase(email).isPresent();
    }

    private boolean checkIfUserExistByPesel(String pesel) {
        return patientRepository.findByPesel(pesel).isPresent();
    }

    public PatientEntity editAccount(PatientEntity patient, EditPatientAccountDto editedData) {
        PatientEntity patientToEdit = patientRepository.findById(patient.getId()).get();
        patientToEdit.update(editedData, passwordEncoder);
        return patientRepository.save(patientToEdit);
    }

    public Page<VaccinationEntity> getAllVaccinationsForPatient(PatientEntity patient, int pageNumber) {
        return vaccinationRepository.findByPatient(patient, Pageable.ofSize(ITEMS_PER_PAGE).withPage(pageNumber));
    }
}
