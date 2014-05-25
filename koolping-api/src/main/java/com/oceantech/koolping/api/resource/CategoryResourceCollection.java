package com.oceantech.koolping.api.resource;

import org.springframework.hateoas.Link;

/**
 * Describes category collection
 *
 * @author Sanjoy Roy
 */
public class CategoryResourceCollection extends ResourceCollection<CategoryResource> {

    public CategoryResourceCollection() {
    }

    public CategoryResourceCollection(Iterable<CategoryResource> content, Link... links) {
        super(content, links);
    }
}
