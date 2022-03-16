package com.example.SOPSbackend.security;

public enum Role {
    DOCTOR ("DOCTOR");

    private final String roleToString;

    Role(String roleToString) {
        this.roleToString = roleToString;
    }

    public String getRoleToString() {
        return roleToString;
    }
}
