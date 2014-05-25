package com.oceantech.koolping.domain;

import org.springframework.data.neo4j.annotation.*;

/**
 * @author Sanjoy Roy
 */
@RelationshipEntity(type = "RATED")
public class Rate {

    @GraphId
    private Long id;
    @StartNode
    private Person person;
    @EndNode
    @Fetch
    private Item item;

    private String rating;

    public Rate() {
    }

    public Rate(Person person, Item item, String rating) {
        this.person = person;
        this.item = item;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rate)) return false;

        Rate rate = (Rate) o;

        if (id != null ? !id.equals(rate.id) : rate.id != null) return false;
        if (item != null ? !item.equals(rate.item) : rate.item != null) return false;
        if (person != null ? !person.equals(rate.person) : rate.person != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (person != null ? person.hashCode() : 0);
        result = 31 * result + (item != null ? item.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "id=" + id +
                ", person=" + person +
                ", item=" + item +
                ", rating='" + rating + '\'' +
                '}';
    }
}
