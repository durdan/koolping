package com.oceantech.koolping.web.controller;

import com.oceantech.koolping.api.resource.PersonForm;
import com.oceantech.koolping.api.resource.PersonResource;
import com.oceantech.koolping.domain.Person;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Sanjoy Roy
 */
public class PersonAssembler {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    public static List<PersonResource> toResources(List<Person> persons){
        List<PersonResource> personResources = new ArrayList<>();
        for(Person person : persons){
            personResources.add(toResource(person));
        }
        return personResources;
    }

    public static List<PersonResource> toResources(Set<Person> persons){
        List<PersonResource> personResources = new ArrayList<>();
        for(Person person : persons){
            personResources.add(toResource(person));
        }
        return personResources;
    }

    public static PersonResource toResource(Person person){
        PersonResource resource = new PersonResource();
        resource.setPersonRef("urn:persons:"+person.getId());
        resource.setUsername(person.getUsername());
        resource.setFirstName(person.getFirstName());
        resource.setLastName(person.getLastName());
        if(person.getEmail() != null){
            resource.setEmail(person.getEmail());
        }
        if(person.getGender() != null){
            resource.setGender(person.getGender());
        }
        if(person.getBirthDate()!=null){
            resource.setBirthDate(DATE_FORMAT.format(person.getBirthDate()));
        }
        resource.add(ControllerLinkBuilder.
                linkTo(ControllerLinkBuilder.
                        methodOn(PersonController.class).
                        getPerson(person.getId()))
                .withSelfRel());
        return resource;
    }

    public static Person toPerson(PersonForm form){
        Person person = new Person();
        person.setUsername(form.getUsername());
        person.setPassword(form.getPassword());
        person.setFirstName(form.getFirstName());
        person.setLastName(form.getLastName());
        person.setEmail(form.getEmail());
        person.setGender(form.getGender());
        try{
            person.setBirthDate(DATE_FORMAT.parse(form.getBirthDate()));
        } catch (ParseException pe){
        }
        return person;
    }
}
