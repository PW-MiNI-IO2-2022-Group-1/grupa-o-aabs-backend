package com.example.SOPSbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditPatientAccountDto {
    @NotBlank(message = "Required field is empty")
    private String firstName;

    @NotBlank(message = "Required field is empty")
    private String lastName;

    @NotBlank(message = "Required field is empty")
    private String password;

    @NotNull(message = "Required field is empty")
    private AddressDto address;
}
