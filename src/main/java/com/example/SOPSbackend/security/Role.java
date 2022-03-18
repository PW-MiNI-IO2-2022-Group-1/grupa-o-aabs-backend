package com.example.SOPSbackend.security;

public enum Role {
    ADMIN ("ROLE_ADMIN"),
    DOCTOR ("ROLE_DOCTOR"),
    PATIENT("ROLE_PATIENT");

    private final String roleToString;

    Role(String roleToString) {
        this.roleToString = roleToString;
    }

    public String getRoleToString() {
        return roleToString;
    }
}
