package com.oceantech.koolping.api.resource;

import org.springframework.hateoas.Link;

/**
 * Describes item collection
 *
 * @author Sanjoy Roy
 */
public class ItemResourceCollection extends ResourceCollection<ItemResource> {

    public ItemResourceCollection() {
    }

    public ItemResourceCollection(Iterable<ItemResource> content, Link... links) {
        super(content, links);
    }
}
