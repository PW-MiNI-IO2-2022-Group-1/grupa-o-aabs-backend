package com.example.SOPSbackend.model;

import com.example.SOPSbackend.dto.NewPatientRegistrationDto;
import com.example.SOPSbackend.security.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientEntity extends BasicUser {
    @Column(nullable = false)
    private String pesel;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private AddressEntity address;

    public PatientEntity(NewPatientRegistrationDto registration, PasswordEncoder passwordEncoder) {
        super(registration.getFirstName(),
                registration.getLastName(),
                registration.getEmail(),
                passwordEncoder.encode(registration.getPassword()));
        this.pesel = registration.getPesel();
        this.address = new AddressEntity(registration.getAddress());
    }

    @Override
    public Role getRole() {
        return Role.PATIENT;
    }
}
