package com.example.SOPSbackend.service;

import com.example.SOPSbackend.dto.*;
import com.example.SOPSbackend.exception.InternalValidationException;
import com.example.SOPSbackend.exception.UserAlreadyExistException;
import com.example.SOPSbackend.model.*;
import com.example.SOPSbackend.repository.AdminRepository;
import com.example.SOPSbackend.repository.DoctorRepository;
import com.example.SOPSbackend.repository.PatientRepository;
import com.example.SOPSbackend.repository.VaccinationRepository;
import com.example.SOPSbackend.security.AuthorizationResult;
import com.example.SOPSbackend.security.Credentials;
import com.example.SOPSbackend.security.Role;
import com.example.SOPSbackend.security.TokenService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 *  <p>I know this is questionable,
 *  but it's been decided that
 *  we are using the AdminService class
 *  for "everything admin"
 *  (everything related to the admins' endpoints).</p>
 *
 *  <p> We could use DoctorService
 *  to modify doctors tables,
 *  but the currently implemented option
 *  seems more logical and clean.</p>
 *
 *  <p>Any and all thoughts about this
 *  are appreciated at
 *  filip.spychala.stud [at symbol] pw.edu.pl.</p>
 */
@Service
@Transactional
public class AdminService {
    private static final int ITEMS_PER_PAGE = 10;
    private final AdminRepository adminRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final VaccinationRepository vaccinationRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AdminService(AdminRepository adminRepository, DoctorRepository doctorRepository, PatientRepository patientRepository, VaccinationRepository vaccinationRepository, PasswordEncoder encoder, TokenService tokenService) {
        this.adminRepository = adminRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.vaccinationRepository = vaccinationRepository;
        this.passwordEncoder = encoder;
        this.tokenService = tokenService;
    }

    public AuthorizationResult logIn(Credentials credentials) {
        AdminEntity admin = adminRepository.findByEmailIgnoreCase(credentials.getEmail()).orElse(null);
        if(admin == null || !passwordEncoder.matches(credentials.getPassword(), admin.getPassword()))
            return AuthorizationResult.failedAuthorization();

        String token = tokenService.getToken(Role.ADMIN, admin);
        return AuthorizationResult.successfulAuthorization(token, admin);
    }

    public Page<DoctorEntity> getAllDoctors(int pageNumber) {
        return doctorRepository.findAll(Pageable.ofSize(ITEMS_PER_PAGE).withPage(pageNumber));
    }

    public Page<PatientEntity> getAllPatients(int pageNumber) {
        return patientRepository.findAll(Pageable.ofSize(ITEMS_PER_PAGE).withPage(pageNumber));
    }

    @Transactional
    public DoctorEntity addDoctor(NewDoctorDto doctor) throws UserAlreadyExistException {
        if (doctorRepository.findByEmailIgnoreCase(doctor.getEmail()).isPresent())
            throw new UserAlreadyExistException("User already exists for this email");  // TODO: (see https://stackoverflow.com/a/36851768) current implementation makes us return 500, which is not what we want (should be 409 or other). Either find a way to choose the http error code or deal with this higher in the callstack

        return doctorRepository.save(new DoctorEntity(doctor.getFirstName(), doctor.getLastName(),
                doctor.getEmail(), passwordEncoder.encode(doctor.getPassword())));
    }

    /**
     * Exception will be thrown if doctor with such doctorId is non-existent.
     */
    public DoctorEntity updateDoctor(String doctorId, EditDoctorDto editDoctor) {
        DoctorEntity doctor = doctorRepository.getById(Long.valueOf(doctorId));
        doctor.setFirstName(editDoctor.getFirstName());
        doctor.setLastName(editDoctor.getLastName());
        doctor.setEmail(editDoctor.getEmail());
        return doctor;
    }

    /**
     * Exception will be thrown if patient with such patientId is non-existent.
     */
    public BasicUserEntity updatePatient(String patientId, EditPatientDto editPatient) {
        return patientRepository.getById(Long.valueOf(patientId)).update(editPatient);
    }

    public Optional<DoctorEntity> getDoctor(Long doctorId) {
        return doctorRepository.findAllById(List.of(doctorId)).stream().findFirst();
    }

    public Optional<PatientEntity> getPatient(long patientId) {
        return patientRepository.findAllById(List.of(patientId)).stream().findFirst();
    }

    public boolean deleteDoctor(Long doctorId) {
        if (!doctorRepository.existsById(doctorId)) return false;
        doctorRepository.deleteById(doctorId);
        return true;
        // TODO: warning trying to delete doctor with doctorId=1 causes java.sql.SQLIntegrityConstraintViolationException. Fix that.
        // TODO: RE: this is actually OK, if the doctor has some vaccinations planned we cannot delete them. Should think about that in the future. Solution: add "CASCADE" to constraints annotation in order to delete in cascade.
    }

    public boolean deletePatient(Long patientId) {
        if (!patientRepository.existsById(patientId)) return false;
        patientRepository.deleteById(patientId); // TODO: this is code duplication. What can we do?
        return true;
        // TODO: warning - we cannot delete patientId=3. Why? java.sql.SQLIntegrityConstraintViolationException
        // TODO: RE: this is actually OK, if the patient has some vaccinations planned we cannot delete them. Should think about that in the future. Solution: add "CASCADE" to constraints annotation in order to delete in cascade.
    }

    public Page<VaccinationEntity> getVaccinations(String disease, Long doctorId, Long patientId, Integer page) {
        HashMap<String, String> invArgs = new HashMap<>();
        if(doctorId != null && !doctorRepository.existsById(doctorId))
            invArgs.put("doctorId", "doctor doesn't exist");
        if(patientId != null && !patientRepository.existsById(patientId))
            invArgs.put("patientId", "patient doesn't exist");
        if(!invArgs.isEmpty())
            throw new InternalValidationException(invArgs);
        Pageable thisPage = PageRequest.of(page - 1, ITEMS_PER_PAGE, Sort.by("vaccinationSlot.date"));
        return vaccinationRepository.getVaccinationsByParams(disease, doctorId, patientId, thisPage);
    }

    public AdminVaccinationReportDto getReportData(String startDate, String endDate) {
        LocalDateTime sDate = null, eDate = null;
        HashMap<String, String> invArgs = new HashMap<>();
        if (startDate == null)
            invArgs.put("startDate", "startDate is required");
        else sDate = LocalDateTime.ofInstant(Instant.parse(startDate), ZoneId.of("UTC"));
        if (endDate == null)
            invArgs.put("startDate", "endDate is required");
        else eDate = LocalDateTime.ofInstant(Instant.parse(endDate), ZoneId.of("UTC"));
        if(!invArgs.isEmpty())
            throw new InternalValidationException(invArgs);
        var queryData = vaccinationRepository.getReportData(sDate, eDate);
        AdminVaccinationReportDto data = new AdminVaccinationReportDto();
        Map<String, ReportDiseaseDto> diseases = new HashMap<>();
        for (var queryRecord: queryData) {
            String disease = (String) queryRecord[0];
            if(!diseases.containsKey(disease)){
                diseases.put(disease, new ReportDiseaseDto(disease, 0, new ArrayList<>()));
            }
            Integer count = ((Number)queryRecord[2]).intValue();
            String vaccine = (String)queryRecord[1];
            diseases.get(disease).getVaccines().add(new ReportVaccineDto(vaccine, count));
            diseases.get(disease).setCount(diseases.get(disease).getCount() + count);
        }
        data.setDiseases(new ArrayList<>(diseases.values()));
        return data;
    }
}
