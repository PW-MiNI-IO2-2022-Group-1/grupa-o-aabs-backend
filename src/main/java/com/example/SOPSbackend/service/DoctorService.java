package com.example.SOPSbackend.service;

import com.example.SOPSbackend.repository.DoctorRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
    private DoctorRepository repository;

    public DoctorService(DoctorRepository doctorRepository, PasswordEncoder encoder) {
        this.repository = doctorRepository;
    }
}
