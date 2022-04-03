package com.example.SOPSbackend.service;

import com.example.SOPSbackend.model.DoctorEntity;
import com.example.SOPSbackend.repository.DoctorRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AdminService {
    private final DoctorRepository doctorRepository;
    private final PasswordEncoder encoder;

    public AdminService(DoctorRepository doctorRepository, PasswordEncoder encoder) {
        this.doctorRepository = doctorRepository;
        this.encoder = encoder;
    }

    @Transactional
    public DoctorEntity addDoctor(DoctorEntity doctor) {
        if(doctorRepository.findByEmailIgnoreCase(doctor.getEmail()).isPresent()) {
            throw new RuntimeException("Account already exists");
        }
        var hashedPass = encoder.encode(doctor.getPassword());
        doctor.setPassword(hashedPass);
        return doctorRepository.save(doctor);
    }
}
