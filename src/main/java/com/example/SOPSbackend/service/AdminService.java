package com.example.SOPSbackend.service;

import com.example.SOPSbackend.model.DoctorEntity;
import com.example.SOPSbackend.repository.DoctorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public class AdminService {
    private static final int ITEMS_PER_PAGE = 10;
    private final DoctorRepository doctorRepository;
    private final PasswordEncoder encoder;

    public AdminService(DoctorRepository doctorRepository, PasswordEncoder encoder) {
        this.doctorRepository = doctorRepository;
        this.encoder = encoder;
    }

    public Page<DoctorEntity> getAllDoctors(int pageNumber) {
        return doctorRepository.findAll(Pageable.ofSize(ITEMS_PER_PAGE).withPage(pageNumber));
    }

    @Transactional
    public DoctorEntity addDoctor(DoctorEntity doctor) {
        if(doctorRepository.findByEmailIgnoreCase(doctor.getEmail()).isPresent())
            throw new RuntimeException("Account already exists"); // TODO: (see https://stackoverflow.com/a/36851768) current implementation makes us return 500, which is not what we want (should be 409 or other). Either find a way to choose the http error code or deal with this higher in the callstack

        var hashedPass = encoder.encode(doctor.getPassword());
        doctor.setPassword(hashedPass);
        return doctorRepository.save(doctor);
    }

    public Optional<DoctorEntity> getDoctor(Long doctorId) {
        return doctorRepository.findAllById(List.of(doctorId)).stream().findFirst();
    }
}
