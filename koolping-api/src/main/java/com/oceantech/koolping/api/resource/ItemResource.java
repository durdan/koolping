package com.oceantech.koolping.api.resource;

import org.springframework.hateoas.ResourceSupport;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Describes a single item
 *
 * @author Sanjoy Roy
 */
public class ItemResource extends ResourceSupport {

    private String itemRef = "";
    private String placeId = "";
    private Set<String> categories = new LinkedHashSet<>();

    public String getItemRef() {
        return itemRef;
    }

    public void setItemRef(String itemRef) {
        this.itemRef = itemRef;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public void setCategories(Set<String> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "ItemResource{" +
                "itemRef='" + itemRef + '\'' +
                ", placeId='" + placeId + '\'' +
                ", categories=" + categories +
                '}';
    }
}
