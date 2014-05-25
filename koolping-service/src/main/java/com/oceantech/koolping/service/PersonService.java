package com.oceantech.koolping.service;

import com.oceantech.koolping.domain.Person;

import java.util.List;

/**
 * @author Sanjoy Roy
 */
public interface PersonService {
    List<Person> findAll();
    Person findById(final Long id);
    Person create(final Person person);
    Person update(final Person person);
    void delete(final Person person);
}
