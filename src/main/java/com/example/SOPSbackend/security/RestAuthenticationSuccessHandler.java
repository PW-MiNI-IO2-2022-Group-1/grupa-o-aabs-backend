package com.example.SOPSbackend.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.SOPSbackend.response.SuccessfulAuthResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
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
    private final ObjectMapper objectMapper;


    public RestAuthenticationSuccessHandler(@Value("${jwt.secret}") String secret,
                                            @Value("${jwt.expiration_time}") int expirationTime,
                                            ObjectMapper objectMapper) {
        this.secret = secret;
        this.expirationTime = expirationTime;
        this.objectMapper = objectMapper;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        BasicUserDetails principal = (BasicUserDetails) authentication.getPrincipal();

        String token = JWT.create()
                .withSubject(principal.getUsername())
                .withClaim("role", principal.getRole().getName())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(Algorithm.HMAC256(secret));

        try {
            var responseBody = new SuccessfulAuthResponse(token, principal);
            responseBody.writeToStream(response.getOutputStream(), objectMapper);
        } catch(Exception e) {
            throw new RuntimeException("Could not map response to json.");
        }
    }

}
