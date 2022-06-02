package com.example.SOPSbackend.security;

import com.example.SOPSbackend.model.AdminEntity;
import com.example.SOPSbackend.model.DoctorEntity;
import com.example.SOPSbackend.model.PatientEntity;
import com.example.SOPSbackend.repository.AdminRepository;
import com.example.SOPSbackend.repository.DoctorRepository;
import com.example.SOPSbackend.repository.PatientRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserService {
    private final AdminRepository adminRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public UserService(AdminRepository adminRepository, DoctorRepository doctorRepository,
                       PatientRepository patientRepository) {
        this.adminRepository = adminRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    public BasicUserDetails loadUserByUsername(Role role, String username) throws UsernameNotFoundException {
        switch(role) {
            case ADMIN:
                AdminEntity adminUser = adminRepository.findByEmailIgnoreCase(username)
                        .orElseThrow(() -> new UsernameNotFoundException("No such user."));
                return new BasicUserDetails(adminUser);
            case DOCTOR:
                DoctorEntity doctorUser = doctorRepository.findByEmailIgnoreCase(username)
                        .orElseThrow(() -> new UsernameNotFoundException("No such user."));
                return new BasicUserDetails(doctorUser);
            case PATIENT:
                PatientEntity patientUser = patientRepository.findByEmailIgnoreCase(username)
                        .orElseThrow(() -> new UsernameNotFoundException("No such user."));
                return new BasicUserDetails(patientUser);
            default:
                return null;
        }
    }
}
