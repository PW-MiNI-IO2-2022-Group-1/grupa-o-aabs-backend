package com.example.SOPSbackend.controller;

import com.example.SOPSbackend.model.Credentials;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DoctorController {
    @PostMapping("/doctor/login")
    public void login(@RequestBody Credentials credentials) { }
}
