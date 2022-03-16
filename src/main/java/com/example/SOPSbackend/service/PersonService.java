package com.example.SOPSbackend.service;

import com.example.SOPSbackend.model.Person;
import com.example.SOPSbackend.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private PersonRepository personRepository;

    // Spring boot scans for classes with annotations such as @Service, @RestController etc.
    // and automatically creates objects for us and handles their lifetimes and dependencies.
    // For example here it will automatically perform dependency injection using
    // class that was generated from our PersonRepository interface
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

    public List<Person> getPeopleByName(String name) {
        return personRepository.getPeopleByFirstNameIgnoreCase(name)
                               .stream().collect(Collectors.toList());
    }
}
