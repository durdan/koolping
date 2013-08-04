package com.oceantech.koolping.domain.model.identity;

import com.oceantech.koolping.domain.model.common.Country;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonManagedReference;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public final class Address {

    @Column(name = "STREET")
    private String street;
    @Column(name = "CITY")
    private String city;
    @Column(name = "ZIPCODE")
    private String zipcode;

    @Getter
    @Setter
    @JsonManagedReference
    @ManyToOne(targetEntity = Country.class)
    @JoinColumn(name = "COUNTRYCODE")
    private Country country;

    protected Address() {
    }

    public Address(String street, String city, String zipcode, Country country) {
        this.setStreet(street);
        this.setCity(city);
        this.setZipcode(zipcode);
        this.setCountry(country);
    }

    public String getStreet() {
        return street;
    }

    private void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    private void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    private void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
