package com.example.SOPSbackend.response.auth;

import com.example.SOPSbackend.model.Patient;
import com.example.SOPSbackend.response.PatientResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessfulPatientAuthResponse {
    private String token;
    private PatientResponse patient;

    public SuccessfulPatientAuthResponse(String token, Patient patient) {
        this.token = token;
        this.patient = new PatientResponse(patient);
    }
}
