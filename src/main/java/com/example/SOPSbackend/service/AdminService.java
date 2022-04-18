package com.example.SOPSbackend.service;

import com.example.SOPSbackend.dto.EditDoctorDto;
import com.example.SOPSbackend.dto.EditPatientDto;
import com.example.SOPSbackend.model.AddressEntity;
import com.example.SOPSbackend.model.BasicUserEntity;
import com.example.SOPSbackend.dto.NewDoctorDto;
import com.example.SOPSbackend.exception.UserAlreadyExistException;
import com.example.SOPSbackend.model.DoctorEntity;
import com.example.SOPSbackend.model.PatientEntity;
import com.example.SOPSbackend.repository.DoctorRepository;
import com.example.SOPSbackend.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final PasswordEncoder encoder;

    public AdminService(DoctorRepository doctorRepository, PatientRepository patientRepository, PasswordEncoder encoder) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.encoder = encoder;
    }

    public Page<DoctorEntity> getAllDoctors(int pageNumber) {
        return doctorRepository.findAll(Pageable.ofSize(ITEMS_PER_PAGE).withPage(pageNumber));
    }

    public Page<PatientEntity> getAllPatients(int pageNumber) {
        return patientRepository.findAll(Pageable.ofSize(ITEMS_PER_PAGE).withPage(pageNumber));
    }

    public DoctorEntity addDoctor(DoctorEntity doctor) {
        if (doctorRepository.findByEmailIgnoreCase(doctor.getEmail()).isPresent())
            throw new RuntimeException("Account already exists"); // TODO: (see https://stackoverflow.com/a/36851768) current implementation makes us return 500, which is not what we want (should be 409 or other). Either find a way to choose the http error code or deal with this higher in the callstack

        var hashedPass = encoder.encode(doctor.getPassword());
        doctor.setPassword(hashedPass);
        return doctorRepository.save(doctor);
    }
  
    // TODO Filip: One of those is redundant, this happened during a merge
    @Transactional
    public DoctorEntity addDoctor(NewDoctorDto doctor) throws UserAlreadyExistException {

        if (doctorRepository.findByEmailIgnoreCase(doctor.getEmail()).isPresent())
            throw new UserAlreadyExistException("User already exists for this email");  // TODO: (see https://stackoverflow.com/a/36851768) current implementation makes us return 500, which is not what we want (should be 409 or other). Either find a way to choose the http error code or deal with this higher in the callstack

        return doctorRepository.save(new DoctorEntity(doctor.getFirstName(), doctor.getLastName(),
                doctor.getEmail(), encoder.encode(doctor.getPassword())));
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
}
