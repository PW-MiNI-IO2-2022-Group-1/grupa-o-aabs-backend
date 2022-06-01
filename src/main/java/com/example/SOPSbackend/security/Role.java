package com.example.SOPSbackend.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    ADMIN ("admin", "ROLE_ADMIN"),
    DOCTOR ("doctor", "ROLE_DOCTOR"),
    PATIENT("patient", "ROLE_PATIENT");

    private final String name;
    private final String label;

    public static Role fromName(String name) {
        for(Role role : Role.values()) {
            if(role.name.equals(name))
                return role;
        }
        return null;
    }
}
