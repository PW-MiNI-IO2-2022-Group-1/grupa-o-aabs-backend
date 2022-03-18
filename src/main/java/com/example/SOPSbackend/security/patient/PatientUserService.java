package com.example.SOPSbackend.security.patient;

import com.example.SOPSbackend.repository.PatientRepository;
import com.example.SOPSbackend.security.UserEntityDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class PatientUserService implements UserDetailsService {
    private final PatientRepository patientRepository;

    public PatientUserService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return patientRepository.findPatientByEmailIgnoreCase(username)
                .map(UserEntityDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found exception"));
    }
}
