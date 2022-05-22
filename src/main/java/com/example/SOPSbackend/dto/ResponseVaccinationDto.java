package com.example.SOPSbackend.dto;


import com.example.SOPSbackend.model.PatientEntity;
import com.example.SOPSbackend.model.VaccinationEntity;
import com.example.SOPSbackend.model.VaccineEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVaccinationDto {
    @NotNull
    private Long id;

    private VaccineEntity vaccine;
    @NotNull
    private PatientEntity patient;
    @NotNull
    private String status;

    public ResponseVaccinationDto(VaccinationEntity vaccinationEntity) {
        this.id = vaccinationEntity.getId();
        this.patient = vaccinationEntity.getPatient();
        this.status = vaccinationEntity.getStatus();
        this.vaccine = vaccinationEntity.getVaccine();
    }
}
