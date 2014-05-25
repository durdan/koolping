package com.oceantech.koolping.api.resource;

import org.springframework.hateoas.ResourceSupport;

/**
 * @author Sanjoy Roy
 */
public class CategoryResource extends ResourceSupport {

    private String categoryRef;
    private String name;

    public String getCategoryRef() {
        return categoryRef;
    }

    public void setCategoryRef(String categoryRef) {
        this.categoryRef = categoryRef;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CategoryResource{" +
                "categoryRef='" + categoryRef + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}