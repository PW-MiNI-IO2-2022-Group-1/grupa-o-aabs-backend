package com.example.SOPSbackend.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.SOPSbackend.model.BasicUserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenService {
    private static final int TOKEN_EXPIRATION_TIME_IN_MS = 3600 * 24;
    @Value("${jwt.secret}") String tokenSecret;

    public TokenService(@Value("${jwt.secret}") String tokenSecret) {
        this.tokenSecret = tokenSecret;
    }

    public String getToken(Role role, BasicUserEntity user) {
        return JWT.create()
                .withSubject(user.getEmail())
                .withClaim("role", role.getName())
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME_IN_MS))
                .sign(Algorithm.HMAC256(tokenSecret));
    }
}
