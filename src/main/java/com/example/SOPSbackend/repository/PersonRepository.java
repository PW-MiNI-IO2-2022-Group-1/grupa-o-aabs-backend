package com.example.SOPSbackend.repository;

import com.example.SOPSbackend.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {  }
