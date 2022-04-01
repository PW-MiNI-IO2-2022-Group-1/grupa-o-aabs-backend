package com.example.SOPSbackend.model;

import com.example.SOPSbackend.security.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DoctorEntity extends BasicUser {
    public DoctorEntity(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password);
    }

    @Override
    public Role getRole() {
        return Role.DOCTOR;
    }
}