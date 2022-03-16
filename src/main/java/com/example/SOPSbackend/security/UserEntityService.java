package com.example.SOPSbackend.security;

import com.example.SOPSbackend.repository.DoctorRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserEntityService implements UserDetailsService {
    private DoctorRepository doctorRepository;

    public UserEntityService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return doctorRepository.findDoctorByEmail(username)
                .map(UserEntityDetails::new)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
