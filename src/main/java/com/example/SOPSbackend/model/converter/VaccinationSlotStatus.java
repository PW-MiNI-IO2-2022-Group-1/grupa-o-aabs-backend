package com.example.SOPSbackend.model.converter;

import com.example.SOPSbackend.model.VaccinationEntity;
import com.example.SOPSbackend.model.VaccinationSlotEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class VaccinationSlotStatus {
    public VaccinationSlotStatus(VaccinationSlotEntity vse, VaccinationEntity vaccination)
    {
        this.vaccinationSlot = vse;
        this.vaccination = vaccination;
    }

    private VaccinationSlotEntity vaccinationSlot;
    private VaccinationEntity vaccination;
}
