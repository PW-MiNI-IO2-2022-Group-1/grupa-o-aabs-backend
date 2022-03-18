package com.example.SOPSbackend.response;

import com.example.SOPSbackend.model.Admin;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    public AdminResponse(Admin admin) {
        this.id = admin.getId();
        this.firstName = admin.getFirstName();
        this.lastName = admin.getLastName();
        this.email = admin.getEmail();
    }
}
