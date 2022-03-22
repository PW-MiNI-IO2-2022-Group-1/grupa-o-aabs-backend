package com.example.SOPSbackend.response;

import com.example.SOPSbackend.model.BasicUser;
import com.example.SOPSbackend.security.BasicUserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

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

        if(user.isAdmin())
            putToMapWithoutPassword(dict, "admin", user.asAdmin());
        else if(user.isDoctor())
            putToMapWithoutPassword(dict, "doctor", user.asDoctor());
        else if(user.isPatient())
            putToMapWithoutPassword(dict, "patient", user.asPatient());
        else
            throw new IllegalArgumentException();

        objectMapper.writeValue(stream, dict);
    }

    private void putToMapWithoutPassword(Map<String, Object> dict, String key, BasicUser user) {
       var password = user.getPassword();
       user.setPassword(null);
       dict.put(key, user);
       user.setPassword(password);
    }

}
