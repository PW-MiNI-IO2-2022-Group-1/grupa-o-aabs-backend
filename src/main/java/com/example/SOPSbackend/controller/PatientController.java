package com.example.SOPSbackend.controller;

import com.example.SOPSbackend.dto.EditPatientAccountDto;
import com.example.SOPSbackend.dto.NewPatientRegistrationDto;
import com.example.SOPSbackend.exception.UserAlreadyExistException;
import com.example.SOPSbackend.model.PatientEntity;
import com.example.SOPSbackend.security.BasicUserDetails;
import com.example.SOPSbackend.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PutMapping("account")
    @Secured({"ROLE_PATIENT"})
    public ResponseEntity<Object> editAccount(@RequestBody @Valid EditPatientAccountDto newData,
                                              @AuthenticationPrincipal BasicUserDetails authPrincipal) {
        PatientEntity patient = (PatientEntity)authPrincipal.getUser();
        return ResponseEntity.ok(patientService.editAccount(patient, newData));
    }
}