package com.example.SOPSbackend.controller;

import com.example.SOPSbackend.dto.EditPatientAccountDto;
import com.example.SOPSbackend.dto.NewPatientRegistrationDto;
import com.example.SOPSbackend.dto.VaccineIdDto;
import com.example.SOPSbackend.exception.AlreadyReservedException;
import com.example.SOPSbackend.exception.UserAlreadyExistException;
import com.example.SOPSbackend.model.PatientEntity;
import com.example.SOPSbackend.model.VaccinationSlotEntity;
import com.example.SOPSbackend.model.VaccineEntity;
import com.example.SOPSbackend.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path="patient")
public class PatientController extends AbstractController {
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

    @GetMapping("vaccines") //TODO: dodać validation error. W tym zrobić jakiegoś enuma na rodzaje chorób.
    @Secured({"ROLE_PATIENT"})
    public ResponseEntity<List<VaccineEntity>> getVaccines(@RequestParam List<String> diseases) {
        return ResponseEntity.ok().body(patientService.getVaccines(diseases));
    }

    @GetMapping("vaccination-slots")
    @Secured({"ROLE_PATIENT"})
    public ResponseEntity<List<VaccinationSlotEntity>>  getAvailableVaccinationSlots() {
        return ResponseEntity.ok().body(patientService.getAvailableVaccinationSlots());
    }

    @PutMapping("vaccination-slots/{vaccinationSlotId}")
    @Secured({"ROLE_PATIENT"})
    public ResponseEntity<Object> reserveVaccinationSlot(
            @PathVariable long vaccinationSlotId,
            @RequestBody VaccineIdDto vaccineId,
            @AuthenticationPrincipal PatientEntity patient
    ) {
        try {
            patientService.reserveVaccinationSlot(vaccineId.getVaccineId(), vaccinationSlotId, patient);
            return ResponseEntity.ok().body(Map.of("success", true));
        } catch (AlreadyReservedException e) {
            return ResponseEntity.status(409).body(Map.of("success", false, "msg", e.getMessage()));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(Map.of("success", false, "msg", e.getMessage()));
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