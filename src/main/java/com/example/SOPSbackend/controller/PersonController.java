package com.example.SOPSbackend.controller;

import com.example.SOPSbackend.model.Person;
import com.example.SOPSbackend.service.PersonService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {
    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    // This annotation makes Spring Boot map URL /person/{name} (GET request) to this function
    @GetMapping("/person/{name}")
    @Secured("DOCTOR")
    public List<Person> getPerson(@PathVariable("name") String name) {
        return personService.getPeopleByName(name);
    }

    @GetMapping("/person/all")
    public List<Person> getPeople() {
        return personService.getAllPeople();
    }

    @PostMapping("/person")
    public void postPerson(@RequestBody Person person) {
        personService.addPerson(person);
    }
}
