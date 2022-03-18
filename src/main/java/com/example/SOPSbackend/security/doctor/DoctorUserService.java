package com.example.SOPSbackend.security.doctor;

import com.example.SOPSbackend.repository.DoctorRepository;
import com.example.SOPSbackend.security.UserEntityDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class DoctorUserService implements UserDetailsService {
    private final DoctorRepository doctorRepository;

    public DoctorUserService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return doctorRepository.findDoctorByEmailIgnoreCase(username)
                .map(UserEntityDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found exception"));
    }
}
