package com.example.SOPSbackend.response;

import com.example.SOPSbackend.model.Address;
import com.example.SOPSbackend.model.Patient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String pesel;
    private Address address;

    public PatientResponse(Patient patient) {
        this.id = patient.getId();
        this.firstName = patient.getFirstName();
        this.lastName = patient.getLastName();
        this.email = patient.getEmail();
        this.pesel = patient.getPesel();
        this.address = patient.getAddress();
    }
}
