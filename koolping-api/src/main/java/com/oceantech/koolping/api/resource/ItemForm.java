package com.oceantech.koolping.api.resource;

import org.springframework.hateoas.ResourceSupport;

/**
 * @author Sanjoy Roy
 */
public class ItemForm extends ResourceSupport {

    private String placeId = "";

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    @Override
    public String toString() {
        return "ItemForm{" +
                "placeId='" + placeId + '\'' +
                '}';
    }
}
