package com.example.SOPSbackend.security.config;

import com.example.SOPSbackend.model.Doctor;
import com.example.SOPSbackend.security.BasicUserService;
import com.example.SOPSbackend.security.RestAuthenticationFailureHandler;
import com.example.SOPSbackend.security.RestAuthenticationSuccessHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class DoctorSecurityConfig extends UserSecurityConfig<Doctor> {

    public DoctorSecurityConfig(PasswordEncoder passwordEncoder, BasicUserService<Doctor> userService,
                                ObjectMapper objectMapper, RestAuthenticationSuccessHandler successHandler,
                                RestAuthenticationFailureHandler failureHandler,
                                @Value("${jwt.secret}") String tokenSecret) {
        super("doctor", passwordEncoder, userService, objectMapper, successHandler, failureHandler, tokenSecret);
    }
}
