package com.example.SOPSbackend.controller;

import com.example.SOPSbackend.dto.*;
import com.example.SOPSbackend.exception.AlreadyReservedException;
import com.example.SOPSbackend.exception.UserAlreadyExistException;
import com.example.SOPSbackend.model.PatientEntity;
import com.example.SOPSbackend.model.VaccinationSlotEntity;
import com.example.SOPSbackend.model.VaccineEntity;
import com.example.SOPSbackend.model.converter.DateTimeConverter;
import com.example.SOPSbackend.security.BasicUserDetails;
import com.example.SOPSbackend.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "patient")
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
            return ResponseEntity.status(201).body(new PatientWithoutPasswordDto(patientService.register(registration)));
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("success", false,
                    "msg", e.getMessage()));
        }
    }

    @PutMapping("account")
    @Secured({"ROLE_PATIENT"})
    public ResponseEntity<Object> editAccount(@RequestBody EditPatientAccountDto newData,
                                              @AuthenticationPrincipal BasicUserDetails authPrincipal) {
        PatientEntity patient = (PatientEntity) authPrincipal.getUser();
        return ResponseEntity.ok(patientService.editAccount(patient, newData));
    }

    @GetMapping("vaccines")
    @Secured({"ROLE_PATIENT"})
    public ResponseEntity<Object> getVaccines(@RequestParam(name = "disease") List<String> diseasesList) {
        for (String disease : diseasesList) {
            if (!VaccineEntity.Disease.isValidDiseaseName(disease)) {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
                        Map.of("success", false,
                                "data", Map.of("diseases", disease + " is not valid disease name. Try: " +
                                        VaccineEntity.Disease.getValidDiseaseNames())));
            }
        }
        return ResponseEntity.ok().body(Map.of("vaccines", patientService.getVaccines(diseasesList)));
    }

    @GetMapping("vaccination-slots")
    @Secured({"ROLE_PATIENT"})
    public ResponseEntity<Object> getAvailableVaccinationSlots() {
        return ResponseEntity.ok().body(patientService.getAvailableVaccinationSlots().stream().map(
                vaccinationSlot -> Map.of("id", vaccinationSlot.getId(),
                        "date", vaccinationSlot.getDate().toString()
                )));
    }

    @PutMapping("vaccination-slots/{vaccinationSlotId}")
    @Secured({"ROLE_PATIENT"})
    public ResponseEntity<Object> reserveVaccinationSlot(@PathVariable long vaccinationSlotId,
                                                         @RequestBody VaccineIdDto vaccineId,
                                                         @AuthenticationPrincipal BasicUserDetails authPrincipal
    ) {
        PatientEntity patient = (PatientEntity) authPrincipal.getUser();

        try {
            patientService.reserveVaccinationSlot(vaccineId.getVaccineId(), vaccinationSlotId, patient);
            return ResponseEntity.ok().body(Map.of("success", true));
        } catch (AlreadyReservedException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("success", false,
                    "msg", e.getMessage()));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("success", false,
                    "msg", e.getMessage()));
        }
    }
}