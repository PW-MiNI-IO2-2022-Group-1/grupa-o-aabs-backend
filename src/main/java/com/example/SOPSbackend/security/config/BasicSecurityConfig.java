package com.example.SOPSbackend.security.config;

import com.example.SOPSbackend.model.Admin;
import com.example.SOPSbackend.model.Doctor;
import com.example.SOPSbackend.model.Patient;
import com.example.SOPSbackend.repository.AdminRepository;
import com.example.SOPSbackend.repository.DoctorRepository;
import com.example.SOPSbackend.repository.PatientRepository;
import com.example.SOPSbackend.security.BasicUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class BasicSecurityConfig {
    @Bean
    public BasicUserService<Admin> adminUserService(AdminRepository repository) {
        return new BasicUserService<>(repository);
    }

    @Bean
    public BasicUserService<Doctor> doctorUserService(DoctorRepository repository) {
        return new BasicUserService<>(repository);
    }

    @Bean
    public BasicUserService<Patient> patientUserService(PatientRepository repository) {
        return new BasicUserService<>(repository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
