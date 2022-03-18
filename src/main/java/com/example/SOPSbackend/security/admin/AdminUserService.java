package com.example.SOPSbackend.security.admin;

import com.example.SOPSbackend.repository.AdminRepository;
import com.example.SOPSbackend.security.UserEntityDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AdminUserService implements UserDetailsService {
    private final AdminRepository adminRepository;

    public AdminUserService(AdminRepository doctorRepository) {
        this.adminRepository = doctorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return adminRepository.findAdminByEmailIgnoreCase(username)
                .map(UserEntityDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found exception"));
    }
}

