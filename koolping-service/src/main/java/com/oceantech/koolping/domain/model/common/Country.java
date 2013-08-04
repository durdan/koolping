package com.oceantech.koolping.domain.model.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Entity
public class Country {

    @Getter
    @Setter
    @Id
    private String countryCode;

    @Getter
    @Setter
    private String countryName;


    public Country(String countryCode,String countryName) {
        this.countryCode=countryCode;
        this.countryName = countryName;

    }

    public Country (){

    }
}
