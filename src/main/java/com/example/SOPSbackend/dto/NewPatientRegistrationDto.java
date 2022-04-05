package com.example.SOPSbackend.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class NewPatientRegistrationDto {
    @NotNull
    private String firstName, lastName, pesel, email, password;
    @NotNull
    private AddressDto address;
}

