package com.example.SOPSbackend.controller;

import com.example.SOPSbackend.model.DoctorEntity;
import com.example.SOPSbackend.response.BasicUserResponse;
import com.example.SOPSbackend.response.PaginatedResponse;
import com.example.SOPSbackend.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                        .map(BasicUserResponse::new)
                )
        );
    }

    @PostMapping("doctors")
    public ResponseEntity<Object> createDoctor(@RequestBody DoctorEntity doctor) {
        return ResponseEntity.ok().body(new BasicUserResponse(adminService.addDoctor(doctor)));
    }
}
