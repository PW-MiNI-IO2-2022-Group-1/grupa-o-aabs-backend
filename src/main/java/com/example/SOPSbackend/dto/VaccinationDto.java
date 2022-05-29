package com.example.SOPSbackend.dto;

import com.example.SOPSbackend.model.VaccinationEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VaccinationDto extends VaccinationEntity { // TODO: Dear reviewer: We both know this is quite a slant, but you can't prove it's not brilliant. Sue me. ~Fili:P
    private BasicUserWithoutPasswordDto doctor;

    public VaccinationDto(VaccinationEntity vaccinationEntity) {
        id = vaccinationEntity.getId();
        vaccine = vaccinationEntity.getVaccine();
        vaccinationSlot = vaccinationEntity.getVaccinationSlot();
        status = vaccinationEntity.getStatus();
        patient = vaccinationEntity.getPatient();
        doctor = new BasicUserWithoutPasswordDto(vaccinationEntity.getVaccinationSlot().getDoctor());
    }
}
