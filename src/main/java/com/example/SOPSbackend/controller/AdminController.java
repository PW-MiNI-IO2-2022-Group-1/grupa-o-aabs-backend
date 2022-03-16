package com.example.SOPSbackend.controller;

import com.example.SOPSbackend.model.Doctor;
import com.example.SOPSbackend.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/admin/doctors")
    public ResponseEntity<Object> createDoctor(@RequestBody Doctor doctor) {
        return ResponseEntity.accepted().body(adminService.addDoctor(doctor));
    }
}
