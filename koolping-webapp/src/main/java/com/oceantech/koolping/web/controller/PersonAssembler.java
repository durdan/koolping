package com.oceantech.koolping.web.controller;

import com.oceantech.koolping.api.ApplicationProtocol;
import com.oceantech.koolping.api.resource.PersonForm;
import com.oceantech.koolping.api.resource.PersonResource;
import com.oceantech.koolping.domain.Person;
import org.apache.commons.lang.StringUtils;
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

    public static List<PersonResource> toResources(List<Person> persons) {
        List<PersonResource> personResources = new ArrayList<>();
        for (Person person : persons) {
            personResources.add(toResource(person));
        }
        return personResources;
    }

    public static List<PersonResource> toResources(Set<Person> persons) {
        List<PersonResource> personResources = new ArrayList<>();
        for (Person person : persons) {
            personResources.add(toResource(person));
        }
        return personResources;
    }

    public static PersonResource toResource(Person person) {
        PersonResource resource = new PersonResource();
        if (person != null) {
            resource.setPersonRef("urn:persons:" + person.getId());
            resource.setUsername(person.getUsername());
            resource.setFirstName(person.getFirstName());
            resource.setLastName(person.getLastName());
            if (person.getEmail() != null) {
                resource.setEmail(person.getEmail());
            }
            if (person.getGender() != null) {
                resource.setGender(person.getGender());
            }
            if (person.getBirthDate() != null) {
                resource.setBirthDate(DATE_FORMAT.format(person.getBirthDate()));
            }
            resource.add(ControllerLinkBuilder.
                    linkTo(ControllerLinkBuilder.
                            methodOn(PersonController.class).
                            getPerson(person.getId()))
                    .withSelfRel());
        }
        return resource;
    }

    public static PersonResource addLinks(Person person, PersonResource resource){
        resource.add(ControllerLinkBuilder.
                linkTo(ControllerLinkBuilder.
                        methodOn(PersonController.class).
                        getFriends(person.getId()))
                .withRel(ApplicationProtocol.FRIENDS));

        resource.add(ControllerLinkBuilder.
                linkTo(ControllerLinkBuilder.
                        methodOn(PersonController.class).
                        rateAVenue(person.getId(), "", "", ""))
                .withRel(ApplicationProtocol.RATE_ITEM));

        resource.add(ControllerLinkBuilder.
                linkTo(ControllerLinkBuilder.
                        methodOn(PersonController.class).
                        deletePerson(person.getId()))
                .withRel(ApplicationProtocol.DELETE_PERSON));

        return resource;
    }

    public static Person toPerson(PersonForm form) {
        Person person = new Person();
        if (StringUtils.isNotEmpty(form.getUsername())) {
            person.setUsername(form.getUsername().trim());
        }
        if (StringUtils.isNotEmpty(form.getPassword())) {
            person.setPassword(form.getPassword());
        }
        if (StringUtils.isNotEmpty(form.getFirstName())) {
            person.setFirstName(form.getFirstName().trim());
        }
        if (StringUtils.isNotEmpty(form.getLastName())) {
            person.setLastName(form.getLastName().trim());
        }
        if (StringUtils.isNotEmpty(form.getEmail())) {
            person.setEmail(form.getEmail().trim());
        }
        if (StringUtils.isNotEmpty(form.getGender())) {
            person.setGender(form.getGender().trim());
        }
        if (StringUtils.isNotEmpty(form.getFacebookId())) {
            person.setFacebookId(form.getFacebookId().trim());
        }
        if (StringUtils.isNotEmpty(form.getTwitterId())) {
            person.setTwitterId(form.getTwitterId().trim());
        }
        if (StringUtils.isNotEmpty(form.getGoogleplusId())) {
            person.setGoogleplusId(form.getGoogleplusId().trim());
        }
        try {
            person.setBirthDate(DATE_FORMAT.parse(form.getBirthDate()));
        } catch (ParseException pe) {
        }
        return person;
    }
}
