package com.example.SOPSbackend.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class AddressDto {
    @NotBlank(message = "Required field is empty")
    private String city;

    @NotBlank(message = "Required field is empty")
    private String zipCode;

    @NotBlank(message = "Required field is empty")
    private String street;

    @NotBlank(message = "Required field is empty")
    private String houseNumber;

    private String localNumber;
}
