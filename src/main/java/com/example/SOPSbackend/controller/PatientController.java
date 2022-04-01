package com.example.SOPSbackend.controller;

import com.example.SOPSbackend.dto.NewPatientRegistrationDto;
import com.example.SOPSbackend.exception.UserAlreadyExistException;
import com.example.SOPSbackend.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path="patient")
public class PatientController extends BasicController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello");
    }

    @PostMapping("registration")
    public ResponseEntity<Object> registerPatient(@RequestBody NewPatientRegistrationDto registration) {
        try {
            return ResponseEntity.ok().body(patientService.register(registration));
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("success", false, "msg", e.getMessage()));
        }
    }
}