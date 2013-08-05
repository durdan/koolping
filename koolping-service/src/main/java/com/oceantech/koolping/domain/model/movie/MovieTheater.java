package com.oceantech.koolping.domain.model.movie;


import com.oceantech.koolping.domain.model.common.Country;

import javax.persistence.*;

@Entity
public class MovieTheater {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long theaterId;
    private String name;
    private String street;
    private String town;
    @OneToOne
    private Country country;

    public Long getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(Long theaterId) {
        this.theaterId = theaterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
