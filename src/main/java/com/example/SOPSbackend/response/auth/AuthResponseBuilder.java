package com.example.SOPSbackend.response.auth;

import com.example.SOPSbackend.security.UserEntityDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.OutputStream;

@Component
public class AuthResponseBuilder {
    private ObjectMapper objectMapper;

    public AuthResponseBuilder(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void buildResponseIntoStream(OutputStream stream, String token, UserEntityDetails user) throws Exception{
        if(user.isAdmin()) {
            var response = new SuccessfulAdminAuthResponse(token, user.asAdmin());
            objectMapper.writeValue(stream, response);
        }
        else if(user.isDoctor()) {
            var response = new SuccessfulDoctorAuthResponse(token, user.asDoctor());
            objectMapper.writeValue(stream, response);
        }
        else if(user.isPatient()) {
            var response = new SuccessfulPatientAuthResponse(token, user.asPatient());
            objectMapper.writeValue(stream, response);
        }
    }
}
