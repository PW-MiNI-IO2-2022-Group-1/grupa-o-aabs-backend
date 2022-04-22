package com.example.SOPSbackend.configuration;

import com.example.SOPSbackend.model.AdminEntity;
import com.example.SOPSbackend.repository.AdminRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * This will be run at the beginning the Spring Boot application
 * (because it's an {@link InitializingBean}). This adds an admin
 * account to the database. The adminEmail and adminPassword
 * are taken from the application.properties file.
 */
@Component
public class MasterAccountConfig implements InitializingBean {
    private final AdminRepository adminRepository;

    private final String adminEmail;
    private final String adminEncodedPassword;

    public MasterAccountConfig(AdminRepository adminRepository, PasswordEncoder passwordEncoder,
                               @Value("${admin.email}") String adminEmail,
                               @Value("${admin.password}") String adminPassword) {
        this.adminRepository = adminRepository;
        this.adminEmail = adminEmail;
        this.adminEncodedPassword = passwordEncoder.encode(adminPassword);
    }

    @Override
    public void afterPropertiesSet() {
        if(adminRepository.findByEmailIgnoreCase(adminEmail).isEmpty()) {
            AdminEntity admin = new AdminEntity();
            admin.setFirstName("test");
            admin.setLastName("test");
            admin.setEmail(adminEmail);
            admin.setPassword(adminEncodedPassword);
            adminRepository.save(admin);
        }
    }
}