package com.oceantech.koolping.domain.model.identity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public final class Address {

    @Column(name = "STREET")
    private String street;
    @Column(name = "CITY")
    private String city;
    @Column(name = "ZIPCODE")
    private String zipcode;
    @Column(name = "COUNTRYCODE")
    private String countrycode;

    protected Address() {
    }

    public Address(String street, String city, String zipcode, String countrycode) {
        this.setStreet(street);
        this.setCity(city);
        this.setZipcode(zipcode);
        this.setCountrycode(countrycode);
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

    public String getCountrycode() {
        return countrycode;
    }

    private void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }
}
