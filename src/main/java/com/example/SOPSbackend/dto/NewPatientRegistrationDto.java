package com.example.SOPSbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NewPatientRegistrationDto {
    @NotNull
    private String firstName, lastName, pesel, email, password;
    @NotNull
    private AddressDto address;
}

