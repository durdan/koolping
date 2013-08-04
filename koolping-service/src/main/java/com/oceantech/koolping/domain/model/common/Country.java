package com.oceantech.koolping.domain.model.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * User: durdan
 * Date: 31/07/13
 * Time: 21:24
 * To change this template use File | Settings | File Templates.
 */

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
