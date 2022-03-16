package com.example.SOPSbackend.security;

public enum Role {
    DOCTOR ("ROLE_DOCTOR");

    private final String roleToString;

    Role(String roleToString) {
        this.roleToString = roleToString;
    }

    public String getRoleToString() {
        return roleToString;
    }
}
