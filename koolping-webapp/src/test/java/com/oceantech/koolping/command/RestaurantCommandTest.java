package com.oceantech.koolping.command;

import com.oceantech.koolping.domain.model.restaurant.Restaurant;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: durdan
 * Date: 07/08/13
 * Time: 16:03
 * To change this template use File | Settings | File Templates.
 */
public class RestaurantCommandTest {

    @Test
    public void shouldConvertToUser() {

        RestaurantCommand restaurantCommand = new RestaurantCommand();
        restaurantCommand.setCity("London");
        restaurantCommand.setCountryName("United Kingdom");
        restaurantCommand.setRestaurantEmail("Test@Email.com");
        restaurantCommand.setRestaurantPhone("9090909090");
        restaurantCommand.setStreet("Test Street");
        restaurantCommand.setRestaurantCuisineName("Indian");
        restaurantCommand.setRestaurantName("100 Degree");
        restaurantCommand.setZipcode("Test");
        Restaurant restaurant = restaurantCommand.convertToRestaurant();

        assertThat(restaurant.getRestaurantName(), is("100 Degree"));
        assertThat(restaurant.getAddress().getCity(), is("London"));
        assertThat(restaurant.getAddress().getCountry().getCountryName(), is("United Kingdom"));
        assertThat(restaurant.getRestaurantCuisineType().getRestaurantCuisineName(), is("Indian"));


    }

    @Test(expectedExceptions = IllegalArgumentException.class)
       public void shouldThrowIllegalArgumentExceptionIfEmailIsNotValid() throws Exception{
           RestaurantCommand command = new RestaurantCommand();
           command.setRestaurantEmail("email");
           command.convertToRestaurant();
       }
}
