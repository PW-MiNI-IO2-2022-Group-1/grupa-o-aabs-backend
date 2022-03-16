package com.example.SOPSbackend.response;

import com.example.SOPSbackend.model.Doctor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    public DoctorResponse(Doctor doctor) {
        this.id = doctor.getId();
        this.firstName = doctor.getFirstName();
        this.lastName = doctor.getLastName();
        this.email = doctor.getEmail();
    }
}
