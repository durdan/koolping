package com.oceantech.koolping.infrastructure.persistence.restaurant;

import com.oceantech.koolping.AbstractKoolpingRepositoryTest;
import com.oceantech.koolping.domain.model.restaurant.RestaurantCuisineType;
import com.oceantech.koolping.infrastructure.persistence.restaurant.RestaurantCuisineTypeRepository;
import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Created with IntelliJ IDEA.
 * User: durdan
 * Date: 30/07/13
 * Time: 22:19
 * To change this template use File | Settings | File Templates.
 */
public class RestaurantCuisineTypeRepositoryTest extends AbstractKoolpingRepositoryTest {

    @Autowired
    private RestaurantCuisineTypeRepository restaurantCuisineTypeRepository;

    @Test
    public void shouldSaveRestaurantCuisineType() {
        RestaurantCuisineType rct = new RestaurantCuisineType("Indian");
        restaurantCuisineTypeRepository.save(rct);
        assertThat(rct.getRestaurantCuisineTypeId(), is(notNullValue()));
    }

    @Test
    public void shouldFetchRestaurantCuisineType() {
        RestaurantCuisineType rct = new RestaurantCuisineType("Indian");
        RestaurantCuisineType rct1 = restaurantCuisineTypeRepository.save(rct);
        assertThat(restaurantCuisineTypeRepository.findOne(rct1.getRestaurantCuisineTypeId()).getRestaurantCuisineName(), Matchers.is("Indian"));
    }

}
