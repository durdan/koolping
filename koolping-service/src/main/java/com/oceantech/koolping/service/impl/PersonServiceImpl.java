package com.oceantech.koolping.service.impl;

import com.oceantech.koolping.annotation.ControlService;
import com.oceantech.koolping.domain.Person;
import com.oceantech.koolping.exception.IllegalKoolPingAction;
import com.oceantech.koolping.repository.PersonRepository;
import com.oceantech.koolping.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;

import java.util.List;

/**
 * Service for managing Person
 *
 * @author Sanjoy Roy
 */

@ControlService
public class PersonServiceImpl implements PersonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceImpl.class);

    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<Person> findAll() {
        return personRepository.findAll().as(List.class);
    }

    @Override
    public Person findById(final Long id) {
        Person person = personRepository.findOne(id);
        if(person == null){
            throw new DataRetrievalFailureException("Id [ "+ id +" ] id not valid");
        }
        return person;
    }

    @Override
    public Person create(final Person person) {
        Person exitedPerson = personRepository.findByUsername(person.getUsername().trim());
        if(exitedPerson == null){
            Person savedPerson = personRepository.save(person);
            LOGGER.info("Person is created {}", savedPerson);
            return savedPerson;
        } else {
            throw new IllegalKoolPingAction("Person with username [ "+person.getUsername()+" ] exists.");
        }
    }

    @Override
    public Person update(Person person) {
        return personRepository.save(person);
    }

    @Override
    public void delete(final Person person) {
        personRepository.delete(person);
    }
}
