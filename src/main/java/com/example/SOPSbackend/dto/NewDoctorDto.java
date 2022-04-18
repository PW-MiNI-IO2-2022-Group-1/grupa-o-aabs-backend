package com.example.SOPSbackend.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class NewDoctorDto {
    @NotNull String firstName, lastName, email, password;
}

