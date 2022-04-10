package com.example.SOPSbackend.service;

import com.example.SOPSbackend.dto.NewDoctorDto;
import com.example.SOPSbackend.exception.UserAlreadyExistException;
import com.example.SOPSbackend.model.DoctorEntity;
import com.example.SOPSbackend.model.PatientEntity;
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
    public DoctorEntity addDoctor(NewDoctorDto doctor) throws UserAlreadyExistException {

        if(doctorRepository.findByEmailIgnoreCase(doctor.getEmail()).isPresent())
            throw new UserAlreadyExistException("User already exists for this email");

        return doctorRepository.save(new DoctorEntity(doctor.getFirstName(), doctor.getLastName(), doctor.getEmail(), encoder.encode(doctor.getPassword())));
    }
}
