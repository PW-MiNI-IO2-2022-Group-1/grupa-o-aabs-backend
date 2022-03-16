package com.example.SOPSbackend.service;

import com.example.SOPSbackend.model.Doctor;
import com.example.SOPSbackend.repository.DoctorRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AdminService {
    private DoctorRepository doctorRepository;
    private PasswordEncoder encoder;

    public AdminService(DoctorRepository doctorRepository, PasswordEncoder encoder) {
        this.doctorRepository = doctorRepository;
        this.encoder = encoder;
    }

    @Transactional
    public Doctor addDoctor(Doctor doctor) {
        if(doctorRepository.findDoctorByEmail(doctor.getEmail()).isPresent()) {
            throw new RuntimeException("Account already exists");
        }
        var hashedPass = encoder.encode(doctor.getPassword());
        doctor.setPassword(hashedPass);
        return doctorRepository.save(doctor);
    }
}
