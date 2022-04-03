package com.example.SOPSbackend.response;

import com.example.SOPSbackend.security.BasicUserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.OutputStream;
import java.util.HashMap;

public class SuccessfulAuthResponse {
    private final String token;
    private final BasicUserDetails user;

    public SuccessfulAuthResponse(String token, BasicUserDetails user) {
        this.token = token;
        this.user = user;
    }

    public void writeToStream(OutputStream stream, ObjectMapper objectMapper) throws Exception {
        HashMap<String, Object> dict = new HashMap<>();
        dict.put("token", token);
        dict.put(user.getRole().getName(), user.getUser());

        objectMapper.writeValue(stream, dict);
    }
}
