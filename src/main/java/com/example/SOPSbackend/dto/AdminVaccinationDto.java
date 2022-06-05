package com.example.SOPSbackend.dto;

import com.example.SOPSbackend.model.DoctorEntity;
import com.example.SOPSbackend.model.PatientEntity;
import com.example.SOPSbackend.model.VaccinationEntity;
import com.example.SOPSbackend.model.VaccineEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminVaccinationDto {
    private Long id;
    private VaccineEntity vaccine;
    private AdminVaccinationSlotDto vaccinationSlot;
    private String status;
    private PatientEntity patient;
    private DoctorEntity doctor;

    public AdminVaccinationDto(VaccinationEntity v) {
        id = v.getId();
        vaccine = v.getVaccine();
        patient = v.getPatient();
        status = v.getStatus();
        vaccinationSlot = new AdminVaccinationSlotDto(v.getVaccinationSlot());
        doctor = v.getVaccinationSlot().getDoctor();
    }
}
