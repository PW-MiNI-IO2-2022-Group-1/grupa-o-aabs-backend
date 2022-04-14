package com.example.SOPSbackend.model;

import com.example.SOPSbackend.security.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@MappedSuperclass
@Getter
@Setter
@JsonSubTypes({
        @JsonSubTypes.Type(value = AdminEntity.class, name = "admin"),
        @JsonSubTypes.Type(value = DoctorEntity.class, name = "doctor"),
        @JsonSubTypes.Type(value = PatientEntity.class, name = "patient")
})
@NoArgsConstructor
public abstract class BasicUserEntity { // TODO: we use this class and it's subclasses as DTOs, there should be a separate class(es) in the dto package (this will solve all confusion with hashed/not-hashed passwords in the system)
    public BasicUserEntity(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false)
    protected String firstName;

    @Column(nullable = false)
    protected String lastName;

    @Column(nullable = false) // TODO: 01/04/2022 Filip , unique = true)
    protected String email;

    @Column(nullable = false)
    @Getter(AccessLevel.NONE)
    protected String password;

    public String getPassword() {
        return password;
    }

    @JsonIgnore
    public Role getRole() {
        return null;
    }
}
