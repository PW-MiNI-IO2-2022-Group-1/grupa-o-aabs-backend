package com.example.SOPSbackend.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    ADMIN ("ROLE_ADMIN"),
    DOCTOR ("ROLE_DOCTOR"),
    PATIENT("ROLE_PATIENT");

    private final String roleToString;
}
