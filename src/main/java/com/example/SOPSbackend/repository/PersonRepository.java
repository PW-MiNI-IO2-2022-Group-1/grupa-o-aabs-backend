package com.example.SOPSbackend.repository;

import com.example.SOPSbackend.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

// Spring boot automatically generates an implementation
// it also generates many simple functions such as findAll or findById
public interface PersonRepository extends JpaRepository<Person, Long> {
    Collection<Person> getPeopleByFirstNameIgnoreCase(@Param("name") String name);
}
