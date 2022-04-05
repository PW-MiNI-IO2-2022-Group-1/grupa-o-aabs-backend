package com.example.SOPSbackend.security.config;

import com.example.SOPSbackend.model.AdminEntity;
import com.example.SOPSbackend.model.DoctorEntity;
import com.example.SOPSbackend.model.PatientEntity;
import com.example.SOPSbackend.repository.AdminRepository;
import com.example.SOPSbackend.repository.DoctorRepository;
import com.example.SOPSbackend.repository.PatientRepository;
import com.example.SOPSbackend.security.BasicUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class BasicSecurityConfig {
    @Bean
    public BasicUserService<AdminEntity> adminUserService(AdminRepository repository) {
        return new BasicUserService<>(repository);
    }

    @Bean
    public BasicUserService<DoctorEntity> doctorUserService(DoctorRepository repository) {
        return new BasicUserService<>(repository);
    }

    @Bean
    public BasicUserService<PatientEntity> patientUserService(PatientRepository repository) {
        return new BasicUserService<>(repository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
