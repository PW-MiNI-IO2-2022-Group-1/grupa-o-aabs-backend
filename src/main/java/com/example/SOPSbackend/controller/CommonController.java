package com.example.SOPSbackend.controller;

import com.example.SOPSbackend.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class CommonController extends AbstractController {
    private PatientService patientService;

    public CommonController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("vaccination-slots")
    @Secured({"ROLE_PATIENT", "ROLE_ADMIN"})
    public ResponseEntity<Object> getAvailableVaccinationSlots() {
        return ResponseEntity.ok().body(patientService.getAvailableVaccinationSlots().stream().map(
                vaccinationSlot -> Map.of("id", vaccinationSlot.getId(),
                        "date", vaccinationSlot.getDate().toString()
                )));
    }
}
