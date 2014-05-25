package com.oceantech.koolping.web.controller;

import com.oceantech.koolping.annotation.BoundaryController;
import com.oceantech.koolping.api.ApplicationProtocol;
import com.oceantech.koolping.api.resource.*;

import com.oceantech.koolping.domain.Item;
import com.oceantech.koolping.domain.Person;
import com.oceantech.koolping.domain.Rate;
import com.oceantech.koolping.service.ItemService;
import com.oceantech.koolping.service.PersonService;

import com.oceantech.koolping.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.neo4j.conversion.EndResult;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

import static com.oceantech.koolping.web.controller.PersonAssembler.toPerson;
import static com.oceantech.koolping.web.controller.PersonAssembler.toResource;
import static com.oceantech.koolping.web.controller.PersonAssembler.toResources;

/**
 * Controller for managing users
 *
 * @author Sanjoy Roy
 */

@RequestMapping(value = "/persons", produces = MediaType.APPLICATION_JSON_VALUE)
@BoundaryController
public class PersonController {

    @Autowired
    private PersonService personService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private RateService rateService;

    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<PersonResourceCollection> getPersons(){

        List<Person> persons = personService.findAll();

        PersonResourceCollection collection = new PersonResourceCollection(toResources(persons));

        collection.add(ControllerLinkBuilder.
                linkTo(ControllerLinkBuilder.
                        methodOn(PersonController.class).
                        getPersonForm()).
                withRel(ApplicationProtocol.PERSON_FORM));

        return new ResponseEntity<>(collection, HttpStatus.OK);
    }

    @RequestMapping(value = "/{personId}", method = RequestMethod.GET)
    public HttpEntity<PersonResource> getPerson(@PathVariable("personId")Long personId){

        Person person = personService.findById(personId);
        PersonResource resource = toResource(person);

        resource.add(ControllerLinkBuilder.
                linkTo(ControllerLinkBuilder.
                        methodOn(PersonController.class).
                        getFriends(person.getId()))
                .withRel(ApplicationProtocol.FRIENDS));

        resource.add(ControllerLinkBuilder.
                linkTo(ControllerLinkBuilder.
                        methodOn(PersonController.class).
                        getItemsForRating(person.getId()))
                .withRel(ApplicationProtocol.RATE_ITEM));

        resource.add(ControllerLinkBuilder.
                linkTo(ControllerLinkBuilder.
                        methodOn(PersonController.class).
                        deletePerson(person.getId()))
                .withRel(ApplicationProtocol.DELETE_PERSON));

        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public HttpEntity<PersonForm> getPersonForm(){
        PersonForm form = new PersonForm();
        form.add(ControllerLinkBuilder.
                linkTo(ControllerLinkBuilder.
                        methodOn(PersonController.class).
                        createPerson(form)).
                withRel(ApplicationProtocol.CREATE_PERSON));
        return new ResponseEntity<>(form, HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<PersonResource> createPerson(@RequestBody PersonForm form){
        Person person = personService.create(toPerson(form));
        PersonResource resource = toResource(person);
        return new ResponseEntity<>(resource, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{personId}/friends", method = RequestMethod.GET)
    public HttpEntity<FriendResourceCollection> getFriends(@PathVariable("personId")Long personId){

        Person person = personService.findById(personId);
        Set<Person> friends = person.getFriends();
        FriendResourceCollection collection = new FriendResourceCollection(toResources(friends));

        collection.add(ControllerLinkBuilder.
                linkTo(ControllerLinkBuilder.
                        methodOn(PersonController.class).
                        getPerson(person.getId()))
                .withRel(ApplicationProtocol.FRIEND_OF));

        return new ResponseEntity<>(collection, HttpStatus.OK);
    }

    @RequestMapping(value = "/{personId}", method = RequestMethod.DELETE)
    public HttpEntity deletePerson(@PathVariable("personId")Long personId){
        Person person = personService.findById(personId);
        personService.delete(person);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{personId}/itemsforrating", method = RequestMethod.GET)
    public HttpEntity<SelectorResource> getItemsForRating(@PathVariable("personId")Long personId){

        Person person = personService.findById(personId);
        SelectorResource resource = new SelectorResource();

        Page<Item> itemPage = itemService.findAll();
        List<ChoiceResource> choices = new ArrayList<>();
        for(Item item : itemPage.getContent()){
            ChoiceResource choice = new ChoiceResource();
            choice.setName(item.getPlaceId());

            choice.add(ControllerLinkBuilder.
                    linkTo(ControllerLinkBuilder.
                            methodOn(PersonController.class).
                            rateAnItem(person.getId(), item.getId(), 1))
                    .withRel(ApplicationProtocol.ONE_STAR));

            choice.add(ControllerLinkBuilder.
                    linkTo(ControllerLinkBuilder.
                            methodOn(PersonController.class).
                            rateAnItem(person.getId(), item.getId(), 2))
                    .withRel(ApplicationProtocol.TWO_STAR));

            choice.add(ControllerLinkBuilder.
                    linkTo(ControllerLinkBuilder.
                            methodOn(PersonController.class).
                            rateAnItem(person.getId(), item.getId(), 3))
                    .withRel(ApplicationProtocol.THREE_STAR));

            choices.add(choice);
        }

        resource.setChoiceResources(choices);

        resource.add(ControllerLinkBuilder.
                linkTo(ControllerLinkBuilder.
                        methodOn(PersonController.class).
                        getItemsForRating(person.getId()))
                .withSelfRel());

        resource.add(ControllerLinkBuilder.
                linkTo(ControllerLinkBuilder.
                        methodOn(PersonController.class).
                        getPerson(person.getId()))
                .withRel(ApplicationProtocol.PERSON));

        return new ResponseEntity<>(resource, HttpStatus.OK);
    }



    @RequestMapping(value = "/{personId}/items/{itemId}/rating/{star}", method = RequestMethod.PUT)
    public HttpEntity<SelectorResource> rateAnItem(@PathVariable("personId")Long personId, @PathVariable("itemId")Long itemId, @PathVariable("star")Integer star){

        Person person = personService.findById(personId);
        Item item = itemService.findById(itemId);

        Rate rate = new Rate(person, item, star);

        rateService.createOrUpdate(rate);

        SelectorResource resource = new SelectorResource();


        return new ResponseEntity<>(resource, HttpStatus.OK);
    }
}
