package com.shop.services;

import com.shop.exceptions.NotFoundException;
import com.shop.models.Person;
import com.shop.repositories.PersonRepository;
import com.shop.validators.PersonValidator;

import java.util.List;
import java.util.Optional;

public class PersonService {
    private final PersonRepository personRepository;
    private final PersonValidator validator;

    public PersonService(
        PersonRepository personRepository,
        PersonValidator validator
    ) {
        this.personRepository = personRepository;
        this.validator = validator;
    }

    public List<Person> getPeople() {
        return personRepository.getPeople();
    }

    public Person addPerson(Person person) {
        validator.validate(person);
        personRepository.addPerson(person);
        return person;
    }

    public Person updatePerson(int id, Person person) {
        validator.validate(id);
        validator.validate(person);
        personRepository.updatePerson(id, person);
        return person;
    }

    public void deletePerson(int id) {
        validator.validate(id);
        personRepository.deletePerson(id);
    }

    public Person getPerson(int id) {
        validator.validate(id);
        Optional<Person> person = personRepository.getPerson(id);
        if (person.isEmpty()) {
            throw new NotFoundException("Person not found");
        } else {
            return person.get();
        }
    }
}
