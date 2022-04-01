package com.example.SOPSbackend.controller;

import com.example.SOPSbackend.model.DoctorEntity;
import com.example.SOPSbackend.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/admin/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello");
    }

    @PostMapping("/admin/doctors")
    public ResponseEntity<Object> createDoctor(@RequestBody DoctorEntity doctor) {
        return ResponseEntity.accepted().body(adminService.addDoctor(doctor));
    }
}
