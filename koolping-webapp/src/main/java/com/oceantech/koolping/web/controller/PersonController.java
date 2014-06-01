package com.oceantech.koolping.web.controller;

import com.oceantech.koolping.annotation.BoundaryController;
import com.oceantech.koolping.api.ApplicationProtocol;
import com.oceantech.koolping.api.resource.*;
import com.oceantech.koolping.domain.Category;
import com.oceantech.koolping.domain.Item;
import com.oceantech.koolping.domain.Person;
import com.oceantech.koolping.domain.Rate;
import com.oceantech.koolping.service.CategoryService;
import com.oceantech.koolping.service.ItemService;
import com.oceantech.koolping.service.PersonService;
import com.oceantech.koolping.service.RateService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.oceantech.koolping.web.controller.PersonAssembler.*;
import static com.oceantech.koolping.web.controller.RatedItemAssembler.toResources;

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
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<PersonResourceCollection> getPersons(@RequestParam("username")String username,
                                                           @RequestParam("facebookId")String facebookId,
                                                           @RequestParam("twitterId")String twitterId,
                                                           @RequestParam("googlePlusId")String googlePlusId){

        List<Person> persons = new ArrayList<>();
        Person person;
        if(StringUtils.isNotBlank(username)){
            person = personService.findByUsername(username.trim());
            if(person != null){
                persons = Arrays.asList(person);
            }
        } else if(StringUtils.isNotBlank(facebookId)){
            person = personService.findByFacebookId(facebookId.trim());
            if(person != null){
                persons = Arrays.asList(person);
            }
        } else if(StringUtils.isNotBlank(twitterId)){
            person = personService.findByTwitterId(twitterId.trim());
            if(person != null){
                persons = Arrays.asList(person);
            }
        } else if(StringUtils.isNotBlank(googlePlusId)){
            person = personService.findByGoogleplusId(googlePlusId.trim());
            if(person != null){
                persons = Arrays.asList(person);
            }
        }

        PersonResourceCollection collection = new PersonResourceCollection();

        if(persons.size() > 0){
            collection = new PersonResourceCollection(toResources(persons));
            collection.setPageSize(persons.size());
            collection.setTotalPages(persons.size());
            collection.setReturnedItems(persons.size());
            collection.setTotalItems(persons.size());
        }

        collection.add(ControllerLinkBuilder.
                linkTo(ControllerLinkBuilder.
                        methodOn(PersonController.class).
                        getPersons(username, facebookId, twitterId, googlePlusId))
                .withSelfRel());

        collection.add(ControllerLinkBuilder.
                linkTo(ControllerLinkBuilder.
                        methodOn(PersonController.class).
                        getPersonForm())
                .withRel(ApplicationProtocol.PERSON_FORM));

        return new ResponseEntity<>(collection, HttpStatus.OK);
    }

    @RequestMapping(value = "/{personId}", method = RequestMethod.GET)
    public HttpEntity<PersonResource> getPerson(@PathVariable("personId")Long personId){

        Person person = personService.findById(personId);
        PersonResource resource = toResource(person);
        resource = addLinks(person, resource);
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
        resource = addLinks(person, resource);
        return new ResponseEntity<>(resource, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{personId}", method = RequestMethod.PUT)
    public HttpEntity<RatedItemResource> rateAVenue(@PathVariable("personId")Long personId,
                                                   @RequestParam("categoryName")String categoryName,
                                                   @RequestParam("placeId")String placeId,
                                                   @RequestParam("rating")String rating){

        Person person = personService.findById(personId);
        Category category = getACategory(categoryName);
        Item item = getAnItem(placeId, category);
        Rate rate = new Rate(person, item, rating);

        Rate newRating = rateService.createOrUpdate(rate);

        RatedItemResource resource = toRatedItemResource(newRating);

        resource.add(ControllerLinkBuilder.
                linkTo(ControllerLinkBuilder.
                        methodOn(PersonController.class).
                        rateAVenue(personId, categoryName, placeId, rating))
                .withSelfRel());

        resource.add(ControllerLinkBuilder.
                linkTo(ControllerLinkBuilder.
                        methodOn(PersonController.class).
                        getPerson(personId))
                .withRel(ApplicationProtocol.PERSON));

        resource.add(ControllerLinkBuilder.
                linkTo(ControllerLinkBuilder.
                        methodOn(ItemController.class).
                        getItem(item.getId()))
                .withRel(ApplicationProtocol.ITEM));

        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    private Category getACategory(String categoryName){
        Category category = categoryService.findByName(categoryName.trim());
        if(category == null){
            Category newCategory = new Category();
            newCategory.setName(categoryName);
            category = categoryService.create(newCategory);
        }
        return category;
    }

    private Item getAnItem(String placeId, Category category){
        Item item = itemService.findByPlaceId(placeId.trim());
        if(item == null){
            Item newItem = new Item();
            newItem.setPlaceId(placeId);
            Set<Category> categories = new HashSet<>();
            categories.add(category);
            newItem.setCategories(categories);
            item = itemService.create(newItem);
        }
        return item;
    }

    private RatedItemResource toRatedItemResource(Rate rate){
        RatedItemResource resource = new RatedItemResource();
        resource.setItemRef("urn:items:"+rate.getItem().getId());
        resource.setPlaceId(rate.getItem().getPlaceId());
        if (!rate.getItem().getCategories().isEmpty()) {
            Set<String> categories = new LinkedHashSet<>(rate.getItem().getCategories().size());
            for (Category itemCategory : rate.getItem().getCategories()) {
                categories.add(itemCategory.getName());
            }
            resource.setCategories(categories);
        }
        resource.setMyRating(rate.getRating());
        resource.setTotalGreenRating(rate.getItem().getTotalGreen());
        resource.setTotalRedRating(rate.getItem().getTotalRed());
        resource.setTotalNeutralRating(rate.getItem().getTotalNeutral());
        return resource;
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

        collection.add(ControllerLinkBuilder.
                linkTo(ControllerLinkBuilder.
                        methodOn(PersonController.class).
                        getFriendForm(person.getId()))
                .withRel(ApplicationProtocol.FRIEND_FORM));

        return new ResponseEntity<>(collection, HttpStatus.OK);
    }

    @RequestMapping(value = "/{personId}/rateditems", method = RequestMethod.GET)
    public HttpEntity<RatedItemResourceCollection> getRatedItems(@PathVariable("personId") Long personId) {

        Person person = personService.findById(personId);

        RatedItemResourceCollection collection = new RatedItemResourceCollection(toResources(person, personService));

        collection.add(ControllerLinkBuilder.
                linkTo(ControllerLinkBuilder.
                        methodOn(PersonController.class).
                        getRatedItems(person.getId()))
                .withSelfRel());

        collection.add(ControllerLinkBuilder.
                linkTo(ControllerLinkBuilder.
                        methodOn(PersonController.class).
                        getPerson(person.getId()))
                .withRel(ApplicationProtocol.PERSON));

        return new ResponseEntity<>(collection, HttpStatus.OK);
    }

    @RequestMapping(value = "/{personId}/friends/form", method = RequestMethod.GET)
    public HttpEntity<PersonForm> getFriendForm(@PathVariable("personId")Long personId){
        Person person = personService.findById(personId);
        PersonForm form = new PersonForm();
        form.add(ControllerLinkBuilder.
                linkTo(ControllerLinkBuilder.
                        methodOn(PersonController.class).
                        createFriend(person.getId(), form)).
                withRel(ApplicationProtocol.CREATE_FRIEND));
        return new ResponseEntity<>(form, HttpStatus.OK);
    }

    @RequestMapping(value = "/{personId}/friends/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<PersonResource> createFriend(@PathVariable("personId")Long personId, @RequestBody PersonForm form){
        Person person = personService.findById(personId);
        Person friend = personService.create(toPerson(form));
        person.knows(friend);
        Person updatedPerson = personService.update(person);
        PersonResource resource = toResource(updatedPerson);
        resource = addLinks(updatedPerson, resource);
        return new ResponseEntity<>(resource, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{personId}", method = RequestMethod.DELETE)
    public HttpEntity deletePerson(@PathVariable("personId")Long personId){
        Person person = personService.findById(personId);
        personService.delete(person);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
