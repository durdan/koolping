package com.oceantech.koolping.domain;


import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.*;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Sanjoy Roy
 */

@NodeEntity
public class Item {
    @GraphId
    private Long id;
    private String placeId;
    @RelatedTo(type = "TYPE_OF")
    @Fetch
    public Set<Category> categories = new LinkedHashSet<>();

    @RelatedToVia(type = "RATED", direction = Direction.INCOMING)
    @Fetch
    Iterable<Rate> ratings;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public int getTotalGreen() {
        int totalGreen = 0;
        for (Rate rate : this.ratings) {
            if (rate.getRating().equalsIgnoreCase("green")) {
                ++totalGreen;
            }
        }
        return totalGreen;
    }

    public int getTotalRed() {
        int totalRed = 0;
        for (Rate rate : this.ratings) {
            if (rate.getRating().equalsIgnoreCase("red")) {
                ++totalRed;
            }
        }
        return totalRed;
    }

    public int getTotalNeutral() {
        int totalNeutral = 0;
        for (Rate rate : this.ratings) {
            if (rate.getRating().equalsIgnoreCase("neutral")) {
                ++totalNeutral;
            }
        }
        return totalNeutral;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;

        Item item = (Item) o;

        if (id != null ? !id.equals(item.id) : item.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", placeId='" + placeId + '\'' +
                ", categories=" + categories +
                '}';
    }
}
