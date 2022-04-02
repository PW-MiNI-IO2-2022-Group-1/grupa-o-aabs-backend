package com.example.SOPSbackend.security.config;

import com.example.SOPSbackend.model.PatientEntity;
import com.example.SOPSbackend.security.BasicUserService;
import com.example.SOPSbackend.security.RestAuthenticationFailureHandler;
import com.example.SOPSbackend.security.RestAuthenticationSuccessHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class PatientSecurityConfig extends UserSecurityConfig<PatientEntity> {
    public PatientSecurityConfig(PasswordEncoder passwordEncoder, BasicUserService<PatientEntity> userService,
                                 ObjectMapper objectMapper, RestAuthenticationSuccessHandler successHandler,
                                 RestAuthenticationFailureHandler failureHandler,
                                 @Value("${jwt.secret}") String tokenSecret) {
        super("patient", passwordEncoder, userService, objectMapper, successHandler, failureHandler,
              tokenSecret);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/patient/registration").permitAll();
        super.configure(http);
    }
}
