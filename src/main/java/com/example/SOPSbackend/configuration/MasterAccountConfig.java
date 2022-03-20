package com.example.SOPSbackend.configuration;

import com.example.SOPSbackend.model.Admin;
import com.example.SOPSbackend.repository.AdminRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MasterAccountConfig implements InitializingBean {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    private String adminEmail;
    private String adminPassword;

    public MasterAccountConfig(AdminRepository adminRepository, PasswordEncoder passwordEncoder,
                               @Value("${admin.email}") String adminEmail,
                               @Value("${admin.password}") String adminPassword) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
        this.adminEmail = adminEmail;
        this.adminPassword = adminPassword;
    }

    @Override
    public void afterPropertiesSet() {
        if(adminRepository.findAdminByEmailIgnoreCase("test@test.com").isEmpty()) {
            Admin admin = new Admin();
            admin.setFirstName("Admin");
            admin.setLastName("Admin");
            admin.setEmail(adminEmail);
            admin.setPassword(passwordEncoder.encode(adminPassword));
            adminRepository.save(admin);
            adminPassword = null;
        }
    }
}