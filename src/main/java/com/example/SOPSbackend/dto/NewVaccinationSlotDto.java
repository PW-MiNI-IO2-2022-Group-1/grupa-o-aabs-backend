package com.example.SOPSbackend.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.Instant;

@Getter
public class NewVaccinationSlotDto {
    @NotNull
    private Instant date;
}
