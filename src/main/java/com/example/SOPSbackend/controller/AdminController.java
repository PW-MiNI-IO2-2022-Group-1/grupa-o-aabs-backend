package com.example.SOPSbackend.controller;

import com.example.SOPSbackend.model.Doctor;
import com.example.SOPSbackend.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("doctors")
    public ResponseEntity<Object> createDoctor(@RequestBody Doctor doctor) {
        return ResponseEntity.accepted().body(adminService.addDoctor(doctor));
    }
}
