package com.example.SOPSbackend.controller;

import com.example.SOPSbackend.dto.BasicUserWithoutPasswordDto;
import com.example.SOPSbackend.dto.EditDoctorDto;
import com.example.SOPSbackend.dto.EditPatientDto;
import com.example.SOPSbackend.dto.PatientWithoutPasswordDto;
import com.example.SOPSbackend.dto.NewDoctorDto;
import com.example.SOPSbackend.exception.UserAlreadyExistException;
import com.example.SOPSbackend.model.DoctorEntity;
import com.example.SOPSbackend.model.PatientEntity;
import com.example.SOPSbackend.response.BasicUserOkResponse;
import com.example.SOPSbackend.response.NotFoundResponse;
import com.example.SOPSbackend.response.PaginatedResponseBody;
import com.example.SOPSbackend.response.SuccessTrueResponse;
import com.example.SOPSbackend.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.Map;

@RestController
@RequestMapping(path = "admin")
@Secured({"ROLE_ADMIN"}) //TODO: check if that prevents other users from using these requests
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello");
    }

    @GetMapping("doctors")
    public ResponseEntity<Object> showDoctors(@RequestParam Optional<Integer> page) {
        return ResponseEntity.ok(new PaginatedResponseBody<>(
                        adminService.getAllDoctors(page.orElse(1) - 1)
                                .map(BasicUserWithoutPasswordDto::new)
                )
        );
    }

    @GetMapping("patients")
    public ResponseEntity<Object> showPatients(@RequestParam Optional<Integer> page) {
        return ResponseEntity.ok(new PaginatedResponseBody<>(
                        adminService.getAllPatients(page.orElse(1) - 1)
                                .map(PatientWithoutPasswordDto::new)
                )
        );
    }

    @PutMapping("doctors/{doctorId}")
    public ResponseEntity<Object> editDoctor(@PathVariable String doctorId, @RequestBody EditDoctorDto editDoctor) {
        try {
            return new BasicUserOkResponse(adminService.updateDoctor(doctorId, editDoctor));
        } catch (EntityNotFoundException e) {
            return new NotFoundResponse();
        }
    }

    @PutMapping("patients/{patientId}")
    public ResponseEntity<Object> editPatient(@PathVariable String patientId, @RequestBody EditPatientDto editPatient) {
        try {
            return new BasicUserOkResponse(adminService.updatePatient(patientId, editPatient));
        } catch (EntityNotFoundException e) {
            return new NotFoundResponse();
        }
    }

    @GetMapping("doctors/{doctorId}")
    public ResponseEntity<Object> getSingleDoctor(@PathVariable String doctorId) {
        Optional<DoctorEntity> doctor = adminService.getDoctor(Long.parseLong(doctorId));
        if (doctor.isPresent())
            return new BasicUserOkResponse(doctor.get()); // TODO: should we have better exception handling? (This could throw NumberFormatException causing a 500 response)
        return new NotFoundResponse();
    }

    @GetMapping("patients/{patientId}")
    public ResponseEntity<Object> showSinglePatient(@PathVariable String patientId) {
        Optional<PatientEntity> patient = adminService.getPatient(Long.parseLong(patientId));
        if (patient.isPresent()) return new BasicUserOkResponse(patient.get());
        return new NotFoundResponse();
    }

    @DeleteMapping("doctors/{doctorId}")
    public ResponseEntity<Object> deleteDoctor(@PathVariable String doctorId) {
        return adminService.deleteDoctor(Long.valueOf(doctorId)) ? new SuccessTrueResponse() : new NotFoundResponse();
    }

    @DeleteMapping("patients/{patientId}")
    public ResponseEntity<Object> deletePatient(@PathVariable String patientId) {
        return adminService.deletePatient(Long.valueOf(patientId)) ? new SuccessTrueResponse() : new NotFoundResponse();
    }

    @PostMapping("doctors")
    public ResponseEntity<Object> createDoctor(@RequestBody NewDoctorDto doctor) {
        try {
            return new BasicUserOkResponse(adminService.addDoctor(doctor));
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(Map.of("success", false, "data", Map.of("email", "Email already exists")));
        }
    }
}
