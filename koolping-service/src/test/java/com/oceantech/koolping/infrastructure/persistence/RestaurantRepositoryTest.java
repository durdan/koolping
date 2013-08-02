package com.oceantech.koolping.infrastructure.persistence;

import com.oceantech.koolping.AbstractKoolpingRepositoryTest;
import com.oceantech.koolping.domain.model.Country;
import com.oceantech.koolping.domain.model.Restaurant;
import com.oceantech.koolping.domain.model.RestaurantCuisineType;
import com.oceantech.koolping.domain.model.identity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Created with IntelliJ IDEA.
 * User: durdan
 * Date: 31/07/13
 * Time: 21:05
 * To change this template use File | Settings | File Templates.
 */
public class RestaurantRepositoryTest extends AbstractKoolpingRepositoryTest {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private CountryRepository countryRepository;


    @Test
    public void shouldSaveRestaurant() {
       Restaurant restaurant= restaurantRepository.save(createRestaurant());
        assertThat(restaurant.getRestaurantId(),is(notNullValue()));

    }


    private Restaurant createRestaurant() {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantName("King Thai Restaurant");
        Country country = new Country("UK","United Kingdom");
        countryRepository.save(country);
        Address address = new Address("Pinner Lane","London","HA5 4RR",country);
        RestaurantCuisineType restaurantCuisineType=new RestaurantCuisineType("Thai");
        restaurant.setAddress(address);
        restaurant.setRestaurantCuisineType(restaurantCuisineType);
        return restaurant;
    }
}
