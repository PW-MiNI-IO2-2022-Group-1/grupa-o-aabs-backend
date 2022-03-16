package com.example.SOPSbackend.service;

import com.example.SOPSbackend.repository.DoctorRepository;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
    private final DoctorRepository repository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.repository = doctorRepository;
    }
}
