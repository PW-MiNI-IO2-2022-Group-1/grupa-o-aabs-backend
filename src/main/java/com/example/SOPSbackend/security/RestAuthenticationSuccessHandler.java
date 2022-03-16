package com.example.SOPSbackend.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.SOPSbackend.model.Doctor;
import com.example.SOPSbackend.repository.DoctorRepository;
import com.example.SOPSbackend.response.SuccessfulDoctorAuthResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
public class RestAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final int expirationTime;
    private final String secret;
    private final DoctorRepository doctorRepository;
    private final ObjectMapper objectMapper;

    public RestAuthenticationSuccessHandler(@Value("${jwt.secret}") String secret,
                                            @Value("${jwt.expiration_time}") int expirationTime,
                                            DoctorRepository doctorRepository,
                                            ObjectMapper objectMapper) {
        this.secret = secret;
        this.expirationTime = expirationTime;
        this.doctorRepository = doctorRepository;
        this.objectMapper = objectMapper;
    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        String token = JWT.create()
                .withSubject(principal.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(Algorithm.HMAC256(secret));

        Doctor doctor = findDoctor(principal);
        var responseJson = new SuccessfulDoctorAuthResponse(token, doctor);
        objectMapper.writeValue(response.getOutputStream(), responseJson);
    }

    private Doctor findDoctor(UserDetails userDetails) {
       return doctorRepository.findDoctorByEmailIgnoreCase(userDetails.getUsername())
               .orElseThrow(() -> new RuntimeException("Database changed during operation"));
    }
}
