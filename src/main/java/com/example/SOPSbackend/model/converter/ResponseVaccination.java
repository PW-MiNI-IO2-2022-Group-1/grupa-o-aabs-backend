package com.example.SOPSbackend.model.converter;


import com.example.SOPSbackend.model.PatientEntity;
import com.example.SOPSbackend.model.VaccinationEntity;
import com.example.SOPSbackend.model.VaccineEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseVaccination {
    public ResponseVaccination(VaccinationEntity vaccinationEntity) {
        this.id = vaccinationEntity.getId();
        this.patient = vaccinationEntity.getPatient();
        this.status = vaccinationEntity.getStatus();
        this.vaccine = vaccinationEntity.getVaccine();
    }
    private Long id;
    private VaccineEntity vaccine;
    private PatientEntity patient;
    private String status;
}
