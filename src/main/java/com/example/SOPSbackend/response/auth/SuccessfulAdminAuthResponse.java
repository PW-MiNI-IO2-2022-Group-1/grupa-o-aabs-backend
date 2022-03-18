package com.example.SOPSbackend.response.auth;

import com.example.SOPSbackend.model.Admin;
import com.example.SOPSbackend.response.AdminResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessfulAdminAuthResponse {
    private String token;
    private AdminResponse admin;

    public SuccessfulAdminAuthResponse(String token, Admin admin) {
        this.token = token;
        this.admin = new AdminResponse(admin);
    }
}
