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
public abstract class BasicUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @Getter(AccessLevel.NONE)
    private String password;

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonIgnore
    public Role getRole() {
        return null;
    }
}
