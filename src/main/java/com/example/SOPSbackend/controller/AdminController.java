package com.example.SOPSbackend.controller;

import com.example.SOPSbackend.dto.NewDoctorDto;
import com.example.SOPSbackend.exception.UserAlreadyExistException;
import com.example.SOPSbackend.model.DoctorEntity;
import com.example.SOPSbackend.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello");
    }

    @PostMapping("doctors")
    public ResponseEntity<Object> createDoctor(@RequestBody NewDoctorDto doctor) {
        try {
            return ResponseEntity.accepted().body(adminService.addDoctor(doctor));
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(Map.of("success", false, "data", Map.of("email", "Email already exists")));
        }
    }
}
