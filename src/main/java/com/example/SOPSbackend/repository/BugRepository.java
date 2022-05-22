package com.example.SOPSbackend.repository;

import com.example.SOPSbackend.model.BugEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BugRepository extends JpaRepository<BugEntity, Long> { }
