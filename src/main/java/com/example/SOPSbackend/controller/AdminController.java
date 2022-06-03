package com.example.SOPSbackend.controller;

import com.example.SOPSbackend.documentUtils.DocUtils;
import com.example.SOPSbackend.dto.*;
import com.example.SOPSbackend.exception.InternalValidationException;
import com.example.SOPSbackend.exception.UserAlreadyExistException;
import com.example.SOPSbackend.model.DoctorEntity;
import com.example.SOPSbackend.model.PatientEntity;
import com.example.SOPSbackend.response.BasicUserOkResponse;
import com.example.SOPSbackend.response.NotFoundResponse;
import com.example.SOPSbackend.response.PaginatedResponseBody;
import com.example.SOPSbackend.response.SuccessTrueResponse;
import com.example.SOPSbackend.security.AuthorizationResult;
import com.example.SOPSbackend.security.Credentials;
import com.example.SOPSbackend.service.AdminService;
import com.itextpdf.text.DocumentException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "admin")
public class AdminController extends AbstractController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello");
    }


    @PostMapping("login")
    public ResponseEntity<Object> logIn(@RequestBody Credentials credentials) {
        AuthorizationResult authResult = adminService.logIn(credentials);
        if(authResult.wasSuccessful()) {
            return ResponseEntity.ok(Map.of("token", authResult.getToken(), "admin", authResult.getUser()));
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("success", false,
                    "msg", "You are not authorized"));
        }
    }

    @GetMapping("doctors")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Object> showDoctors(@RequestParam Optional<Integer> page) {
        return ResponseEntity.ok(new PaginatedResponseBody<>(
                        adminService.getAllDoctors(page.orElse(1) - 1)
                                .map(BasicUserWithoutPasswordDto::new))
        );
    }

    @GetMapping("patients")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Object> showPatients(@RequestParam Optional<Integer> page) {
        return ResponseEntity.ok(new PaginatedResponseBody<>(
                        adminService.getAllPatients(page.orElse(1) - 1)
                                .map(PatientWithoutPasswordDto::new)
                )
        );
    }

    @PutMapping("doctors/{doctorId}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Object> editDoctor(@PathVariable String doctorId, @RequestBody EditDoctorDto editDoctor) {
        try {
            return new BasicUserOkResponse(adminService.updateDoctor(doctorId, editDoctor));
        } catch (EntityNotFoundException e) {
            return new NotFoundResponse();
        }
    }

    @PutMapping("patients/{patientId}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Object> editPatient(@PathVariable String patientId, @RequestBody EditPatientDto editPatient) {
        try {
            return new BasicUserOkResponse(adminService.updatePatient(patientId, editPatient));
        } catch (EntityNotFoundException e) {
            return new NotFoundResponse();
        }
    }

    @GetMapping("doctors/{doctorId}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Object> getSingleDoctor(@PathVariable String doctorId) {
        Optional<DoctorEntity> doctor = adminService.getDoctor(Long.parseLong(doctorId));
        if (doctor.isPresent())
            return new BasicUserOkResponse(doctor.get()); // TODO: should we have better exception handling? (This could throw NumberFormatException causing a 500 response)
        return new NotFoundResponse();
    }

    @GetMapping("patients/{patientId}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Object> showSinglePatient(@PathVariable String patientId) {
        Optional<PatientEntity> patient = adminService.getPatient(Long.parseLong(patientId));
        if (patient.isPresent()) return new BasicUserOkResponse(patient.get());
        return new NotFoundResponse();
    }

    @DeleteMapping("doctors/{doctorId}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Object> deleteDoctor(@PathVariable String doctorId) {
        return adminService.deleteDoctor(Long.valueOf(doctorId)) ? new SuccessTrueResponse() : new NotFoundResponse();
    }

    @DeleteMapping("patients/{patientId}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Object> deletePatient(@PathVariable String patientId) {
        return adminService.deletePatient(Long.valueOf(patientId)) ? new SuccessTrueResponse() : new NotFoundResponse();
    }

    @PostMapping("doctors")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Object> createDoctor(@RequestBody NewDoctorDto doctor) {
        try {
            return new BasicUserOkResponse(adminService.addDoctor(doctor));
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(Map.of("success", false, "data", Map.of("email", "Email already exists")));
        }
    }

    @GetMapping("vaccinations/report")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Object> generateReport(@RequestParam String startDate,
                                                 @RequestParam String endDate) {
        try {
            var data = adminService.getReportData(startDate, endDate);
            return ResponseEntity.ok().body(data);
        } catch (InternalValidationException e){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(Map.of("success", false, "data", e.getErrors()));
        }
    }

    @GetMapping("vaccinations/report/download")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Object> downloadReport(@RequestParam String startDate,
                                                 @RequestParam String endDate) {
        try {
            var cert = DocUtils.convertToBaosPDF(adminService.getReportData(startDate, endDate));
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"adminVaccinationReport_" + startDate + "_to_" + endDate + ".pdf\"");
            return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(cert.toByteArray());
        } catch (InternalValidationException e){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(Map.of("success", false, "data", e.getErrors()));
        } catch ( DocumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("success", false, "data", e.getCause()));
        }
    }
}
