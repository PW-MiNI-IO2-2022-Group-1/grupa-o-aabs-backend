package com.example.SOPSbackend.dto;

import com.example.SOPSbackend.model.AddressEntity;
import com.example.SOPSbackend.model.PatientEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NewPatientAfterRegistrationDto {
    @NotNull
    private long id;
    @NotNull
    private String firstName, lastName, pesel, email;
    @NotNull
    private AddressEntity address;

    public NewPatientAfterRegistrationDto(PatientEntity patientEntity) {
        id = patientEntity.getId();
        firstName = patientEntity.getFirstName();
        lastName = patientEntity.getLastName();
        pesel = patientEntity.getPesel();
        email = patientEntity.getEmail();
        address = patientEntity.getAddress();
    }
}
