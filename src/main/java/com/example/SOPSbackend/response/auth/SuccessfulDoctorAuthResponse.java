package com.example.SOPSbackend.response.auth;

import com.example.SOPSbackend.model.Doctor;
import com.example.SOPSbackend.response.DoctorResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessfulDoctorAuthResponse {
    private String token;
    private DoctorResponse doctor;

    public SuccessfulDoctorAuthResponse(String token, Doctor doctor) {
        this.token = token;
        this.doctor = new DoctorResponse(doctor);
    }
}
