package com.oceantech.koolping.api.resource;

import org.springframework.hateoas.ResourceSupport;

/**
 * @author Sanjoy Roy
 */
public class ChoiceResource extends ResourceSupport {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
