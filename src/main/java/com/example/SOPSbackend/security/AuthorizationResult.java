package com.example.SOPSbackend.security;

import com.example.SOPSbackend.model.BasicUserEntity;

public class AuthorizationResult {
    private final String token;
    private final BasicUserEntity user;

    private AuthorizationResult(String token, BasicUserEntity user) {
        this.token = token;
        this.user = user;
    }

    public static AuthorizationResult failedAuthorization() {
        return new AuthorizationResult(null, null);
    }

    public static AuthorizationResult successfulAuthorization(String token, BasicUserEntity user) {
        return new AuthorizationResult(token, user);
    }

    public String getToken() {
        return token;
    }

    public BasicUserEntity getUser() {
        return user;
    }

    public boolean wasSuccessful() {
        return token != null;
    }
}
