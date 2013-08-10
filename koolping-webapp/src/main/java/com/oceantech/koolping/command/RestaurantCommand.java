package com.oceantech.koolping.command;

import com.oceantech.koolping.domain.model.common.Country;
import com.oceantech.koolping.domain.model.identity.Address;
import com.oceantech.koolping.domain.model.identity.Email;
import com.oceantech.koolping.domain.model.restaurant.Restaurant;
import com.oceantech.koolping.domain.model.restaurant.RestaurantCuisineType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created with IntelliJ IDEA.
 * User: durdan
 * Date: 06/08/13
 * Time: 21:10
 * To change this template use File | Settings | File Templates.
 */

@ToString(callSuper = true)
public class RestaurantCommand {


    @Getter
    @Setter
    private String restaurantName;

    @Getter
    @Setter
    private String restaurantEmail;

    @Getter
    @Setter
    private String street;

    @Getter
    @Setter
    private String city;

    @Getter
    @Setter
    private String zipcode;

    @Getter
    @Setter
    private String countryCode;

    @Getter
    @Setter
    private String countryName;

    @Getter
    @Setter
    private String RestaurantPhone;

    @Getter
    @Setter
    private String RestaurantWebsiteText;

    @Getter
    @Setter
    private String restaurantCuisineName;

    public Restaurant convertToRestaurant() {
        Restaurant restaurant = new Restaurant();
        Country country = new Country();
        country.setCountryName(getCountryName());
        country.setCountryCode(getCountryCode());
        Address address = new Address(getStreet(), getCity(), getZipcode(), country);
        restaurant.setAddress(address);
        RestaurantCuisineType restaurantCuisineType = new RestaurantCuisineType();
        restaurantCuisineType.setRestaurantCuisineName(getRestaurantCuisineName());
        restaurant.setRestaurantCuisineType(restaurantCuisineType);
        restaurant.setRestaurantEmail(new Email(getRestaurantEmail()));
        restaurant.setRestaurantPhone(getRestaurantPhone());
        restaurant.setRestaurantWebsiteText(getRestaurantWebsiteText());
        restaurant.setRestaurantName(getRestaurantName());
        return restaurant;
    }


}
