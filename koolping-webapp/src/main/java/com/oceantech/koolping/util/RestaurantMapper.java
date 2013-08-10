package com.oceantech.koolping.util;

import com.oceantech.koolping.command.RestaurantCommand;
import com.oceantech.koolping.domain.model.restaurant.Restaurant;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: durdan
 * Date: 06/08/13
 * Time: 21:22
 * To change this template use File | Settings | File Templates.
 */
public class RestaurantMapper {

    public static RestaurantCommand map(Restaurant restaurant) {
        RestaurantCommand dto = new RestaurantCommand();
        dto.setRestaurantName(restaurant.getRestaurantName());
        dto.setRestaurantCuisineName(restaurant.getRestaurantCuisineType().getRestaurantCuisineName());
        dto.setRestaurantWebsiteText(restaurant.getRestaurantWebsiteText());
        dto.setRestaurantEmail(restaurant.getRestaurantEmail().toString());
        dto.setRestaurantPhone(restaurant.getRestaurantPhone());
        dto.setCity(restaurant.getAddress().getCity());
        dto.setCountryName(restaurant.getAddress().getCountry().getCountryName());
        dto.setStreet(restaurant.getAddress().getStreet());
        dto.setZipcode(restaurant.getAddress().getZipcode());

        return dto;
    }

    public static List<RestaurantCommand> map(Page<Restaurant> restaurants) {
        List<RestaurantCommand> dtos = new ArrayList<RestaurantCommand>();
        for (Restaurant restaurant : restaurants) {
            dtos.add(map(restaurant));
        }
        return dtos;
    }
}
