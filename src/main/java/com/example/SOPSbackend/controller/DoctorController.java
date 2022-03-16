package com.example.SOPSbackend.controller;

import com.example.SOPSbackend.security.Credentials;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DoctorController {
    @PostMapping("/doctor/login")
    public ResponseEntity login(@RequestBody Credentials credentials) {
        return (ResponseEntity) ResponseEntity.ok();
    }
}
