package com.oceantech.koolping.api.resource;

import org.springframework.hateoas.Link;

/**
 * Describes person collection
 *
 * @author Sanjoy Roy
 */
public class PersonResourceCollection extends ResourceCollection<PersonResource> {

    public PersonResourceCollection() {
    }

    public PersonResourceCollection(Iterable<PersonResource> content, Link... links) {
        super(content, links);
    }
}
