package com.oceantech.koolping.service.impl;

import com.oceantech.koolping.annotation.ControlService;
import com.oceantech.koolping.domain.Item;
import com.oceantech.koolping.domain.Person;
import com.oceantech.koolping.exception.IllegalKoolPingAction;
import com.oceantech.koolping.repository.PersonRepository;
import com.oceantech.koolping.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.util.StringUtils;

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
    public Person findByUsername(final String username) {
        return personRepository.findByUsername(username.trim());
    }

    @Override
    public Person findByFacebookId(final String facebookId) {
        return personRepository.findByFacebookId(facebookId.trim());
    }

    @Override
    public Person findByTwitterId(final String twitterId) {
        return personRepository.findByTwitterId(twitterId.trim());
    }

    @Override
    public Person findByGoogleplusId(final String googleplusId) {
        return personRepository.findByGoogleplusId(googleplusId.trim());
    }

    @Override
    public Person create(final Person person) {
        Person exitedPerson = null;
        if(!StringUtils.isEmpty(person.getUsername())){
            exitedPerson = findByUsername(person.getUsername());
        }
        if((exitedPerson == null) && (!StringUtils.isEmpty(person.getFacebookId()))){
            exitedPerson = findByFacebookId(person.getFacebookId());
        }
        if((exitedPerson == null) && (!StringUtils.isEmpty(person.getTwitterId()))){
            exitedPerson = findByTwitterId(person.getTwitterId());
        }
        if((exitedPerson == null) && (!StringUtils.isEmpty(person.getGoogleplusId()))){
            exitedPerson = findByGoogleplusId(person.getGoogleplusId());
        }

        if(exitedPerson == null){
            Person savedPerson = personRepository.save(person);
            LOGGER.info("Person is created {}", savedPerson);
            return savedPerson;
        } else {
            throw new IllegalKoolPingAction("Person with username [ "+person.getUsername()+" ] exists.");
        }
    }

    @Override
    public Person update(final Person person) {
        return personRepository.save(person);
    }

    @Override
    public void delete(final Person person) {
        personRepository.delete(person);
    }

    @Override
    public String findMyRating(final Person person, final Item item) {
        return personRepository.findMyRating(person, item);
    }

    @Override
    public Integer findMyFriendsRating(final Person person, final Item item, final String rating) {
        return personRepository.findMyFriendsRating(person, item, rating);
    }
}
