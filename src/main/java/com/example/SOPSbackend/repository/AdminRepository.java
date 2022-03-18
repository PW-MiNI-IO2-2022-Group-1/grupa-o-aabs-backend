package com.example.SOPSbackend.repository;

import com.example.SOPSbackend.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findAdminByEmailIgnoreCase(String email);
}
