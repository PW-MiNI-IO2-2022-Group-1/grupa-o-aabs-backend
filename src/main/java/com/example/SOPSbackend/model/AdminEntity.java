package com.example.SOPSbackend.model;

import com.example.SOPSbackend.security.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdminEntity extends BasicUser {
    @Override
    public Role getRole() {
        return Role.ADMIN;
    }
}
