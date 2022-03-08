package com.example.SOPSbackend.service;

import com.example.SOPSbackend.model.Person;
import com.example.SOPSbackend.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person getPersonById(long id) {
       return personRepository.findById(id).get();
    }

    public List<Person> getAllPeople() {
        return personRepository.findAll();
    }

    public Person addPerson(Person person) {
        return personRepository.save(person);
    }
}
