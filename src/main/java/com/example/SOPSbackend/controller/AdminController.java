package com.example.SOPSbackend.controller;

import com.example.SOPSbackend.dto.BasicUserWithoutPasswordDto;
import com.example.SOPSbackend.dto.EditDoctorDto;
import com.example.SOPSbackend.model.DoctorEntity;
import com.example.SOPSbackend.response.BasicUserOkResponse;
import com.example.SOPSbackend.response.NotFoundResponse;
import com.example.SOPSbackend.response.PaginatedResponse;
import com.example.SOPSbackend.response.SuccessTrueResponse;
import com.example.SOPSbackend.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@RestController
@RequestMapping(path="admin")
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
        return ResponseEntity.ok(new PaginatedResponse<>(
                adminService.getAllDoctors(page.orElse(0))
                        .map(BasicUserWithoutPasswordDto::new)
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

    @GetMapping("doctors/{doctorId}")
    public ResponseEntity<Object> getSingleDoctor(@PathVariable String doctorId) {
        Optional<DoctorEntity> doctor = adminService.getDoctor(Long.parseLong(doctorId));
        if (doctor.isPresent()) return new BasicUserOkResponse(doctor.get()); // TODO: should we have better exception handling? (This could throw NumberFormatException causing a 500 response)
        return new NotFoundResponse();
    }

    @DeleteMapping("doctors/{doctorId}")
    public ResponseEntity<Object> deleteDoctor(@PathVariable String doctorId) {
        return adminService.deleteDoctor(Long.valueOf(doctorId)) ? new SuccessTrueResponse() : new NotFoundResponse();
    }

    @PostMapping("doctors")
    public ResponseEntity<Object> createDoctor(@RequestBody DoctorEntity doctor) {
        return new BasicUserOkResponse(adminService.addDoctor(doctor));
    }
}
