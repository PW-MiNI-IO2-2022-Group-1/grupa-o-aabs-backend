package com.example.SOPSbackend.response;

import com.example.SOPSbackend.model.Doctor;
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
