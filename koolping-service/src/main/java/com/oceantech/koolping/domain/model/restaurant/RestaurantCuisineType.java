package com.oceantech.koolping.domain.model.restaurant;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * User: durdan
 * Date: 30/07/13
 * Time: 21:24
 * To change this template use File | Settings | File Templates.
 */

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Entity
public class RestaurantCuisineType {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long restaurantCuisineTypeId;

    @Getter
    @Setter
    private String restaurantCuisineName;


    public RestaurantCuisineType(String restaurantCuisineName) {
        this.restaurantCuisineName = restaurantCuisineName;
    }

    public RestaurantCuisineType(Long restaurantCuisineTypeId ) {
         this.restaurantCuisineTypeId= restaurantCuisineTypeId;
      }

    public RestaurantCuisineType() {
        //To change body of created methods use File | Settings | File Templates.
    }
}
