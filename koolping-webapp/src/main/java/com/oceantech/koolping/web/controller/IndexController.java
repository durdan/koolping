package com.oceantech.koolping.web.controller;

import com.oceantech.koolping.api.ApplicationProtocol;
import com.oceantech.koolping.api.resource.VoidResource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Sanjoy Roy
 */
@RequestMapping(value = "/" , produces = MediaType.APPLICATION_JSON_VALUE)
@Controller
public class IndexController {

    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<VoidResource> getIndexRoot() {

        VoidResource resource = new VoidResource();

        resource.add(ControllerLinkBuilder.
                linkTo(ControllerLinkBuilder.
                        methodOn(CategoryController.class).
                        getCategories()).
                withRel(ApplicationProtocol.CATEGORIES));

        resource.add(ControllerLinkBuilder.
                linkTo(ControllerLinkBuilder.
                        methodOn(ItemController.class).
                        getItems()).
                withRel(ApplicationProtocol.ITEMS));

        resource.add(ControllerLinkBuilder.
                linkTo(ControllerLinkBuilder.
                        methodOn(PersonController.class).
                        getPersons("", "", "", "")).
                withRel(ApplicationProtocol.PERSONS));

        resource.add(ControllerLinkBuilder.
                linkTo(ControllerLinkBuilder.
                        methodOn(PersonController.class).
                        getPersonForm()).
                withRel(ApplicationProtocol.PERSON_FORM));

        return new ResponseEntity<>(resource, HttpStatus.OK);
    }
}
