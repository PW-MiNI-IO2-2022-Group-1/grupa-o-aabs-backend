package com.example.SOPSbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DoctorController {
    @GetMapping("/doctor/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello");
    }
}
