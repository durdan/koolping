package com.oceantech.koolping.api.resource;

import org.springframework.hateoas.Link;

/**
 * Describes friend collection
 *
 * @author Sanjoy Roy
 */
public class FriendResourceCollection extends ResourceCollection<PersonResource> {

    public FriendResourceCollection() {
    }

    public FriendResourceCollection(Iterable<PersonResource> content, Link... links) {
        super(content, links);
    }
}
