package com.example.SOPSbackend.dto;

import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class EditPatientAccountDto {
    @NotBlank(message = "Required field is empty")
    private String firstName;

    @NotBlank(message = "Required field is empty")
    private String lastName;

    @NotBlank(message = "Required field is empty")
    private String password;

    @NotNull(message = "Required field is empty")
    @Valid
    private AddressDto address;
}
