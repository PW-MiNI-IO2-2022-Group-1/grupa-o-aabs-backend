package com.example.SOPSbackend.service;

import com.example.SOPSbackend.documentUtils.DocUtils;
import com.example.SOPSbackend.dto.EditPatientAccountDto;
import com.example.SOPSbackend.dto.NewPatientRegistrationDto;
import com.example.SOPSbackend.exception.AlreadyReservedException;
import com.example.SOPSbackend.exception.InternalValidationException;
import com.example.SOPSbackend.exception.InvalidVaccinationStateException;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Pair;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.*;

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
        return vaccinationRepository.findByPatient(patient, PageRequest.of(pageNumber, 1, Sort.by("vaccinationSlot.date").descending()));
    }

    public Pair<ByteArrayOutputStream, String> downloadCertificate(long vaccinationId) throws DocumentException, InvalidVaccinationStateException {
        Optional<VaccinationEntity> vaccination = vaccinationRepository.findById(vaccinationId);
        if(vaccination.isEmpty())
            throw new NoSuchElementException("Vaccination not found");
        if(!Objects.equals(vaccination.get().getStatus(), "Completed"))
            throw new InvalidVaccinationStateException("Vaccination hasn't been completed yet");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document certificate = new Document();
        VaccinationEntity vacc = vaccination.get();
        PatientEntity patient = vacc.getPatient();
        VaccineEntity v = vacc.getVaccine();
        String title = "Certificate of vaccination";
        String[] lines = {
                "Vaccine:",
                "\t- Name: " + v.getName(),
                "\t- Disease: " + v.getDisease(),
                "Patient: " + patient.getFirstName() + " " + patient.getLastName(),
                "PESEL: " + patient.getPesel(),
        };
        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
        PdfWriter.getInstance(certificate, baos);
        certificate.open();
        DocUtils.addMetadata(certificate, title, patient.getLastName() + " " + patient.getFirstName());
        DocUtils.addParagraph(certificate, title, lines, font);
        certificate.close();
        return Pair.of(baos, patient.getLastName() + "_" + patient.getFirstName() + "_certificate.pdf");
    }
}
