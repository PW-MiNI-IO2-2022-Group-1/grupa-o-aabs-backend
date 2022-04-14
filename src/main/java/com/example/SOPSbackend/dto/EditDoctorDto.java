package com.example.SOPSbackend.dto;

import com.example.SOPSbackend.model.BasicUserEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class EditDoctorDto {
    private final String firstName, lastName, email;

    @JsonCreator
    public EditDoctorDto(@JsonProperty("firstName") String firstName, @JsonProperty("lastName") String lastName, @JsonProperty("email") String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
