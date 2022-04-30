package com.example.SOPSbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class NewDoctorDto {
    @NotNull String firstName, lastName, email, password;
}

