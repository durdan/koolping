package com.oceantech.koolping.api.resource;

import org.springframework.hateoas.Link;

/**
 * Describes rated item collection
 *
 * @author Sanjoy Roy
 */
public class RatedItemResourceCollection extends ResourceCollection<RatedItemResource> {

    public RatedItemResourceCollection() {
    }

    public RatedItemResourceCollection(Iterable<RatedItemResource> content, Link... links) {
        super(content, links);
    }
}
