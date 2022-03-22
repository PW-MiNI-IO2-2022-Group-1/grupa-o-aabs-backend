package com.example.SOPSbackend.model;

import com.example.SOPSbackend.security.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Patient extends BasicUser {
    @Column(nullable = false)
    private String pesel;

    @OneToOne(fetch = FetchType.EAGER)
    private Address address;

    @Override
    public Role getRole() {
        return Role.PATIENT;
    }
}
