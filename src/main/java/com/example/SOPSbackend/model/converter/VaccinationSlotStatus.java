package com.example.SOPSbackend.model.converter;

import com.example.SOPSbackend.model.VaccinationEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class VaccinationSlotStatus {
    private long id;
    private Instant date;
    private VaccinationEntity vaccination;
}
