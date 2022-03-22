package com.example.SOPSbackend.model;

import com.example.SOPSbackend.security.Role;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class Admin extends BasicUser {
    @Override
    public Role getRole() {
        return Role.ADMIN;
    }
}
