package com.oceantech.koolping.infrastructure.persistence.restaurant;

import com.oceantech.koolping.AbstractKoolpingRepositoryTest;
import com.oceantech.koolping.domain.model.identity.User;
import com.oceantech.koolping.domain.model.restaurant.Restaurant;
import com.oceantech.koolping.domain.model.restaurant.ReviewRestaurant;
import com.oceantech.koolping.domain.model.restaurant.ReviewRestaurantPK;
import com.oceantech.koolping.infrastructure.persistence.UserRepository;
import com.oceantech.koolping.infrastructure.persistence.restaurant.RestaurantRepository;
import com.oceantech.koolping.infrastructure.persistence.restaurant.ReviewRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

/**
 * Created with IntelliJ IDEA.
 * User: durdan
 * Date: 05/08/13
 * Time: 20:46
 * To change this template use File | Settings | File Templates.
 */
public class ReviewRestaurantRepositoryTest extends AbstractKoolpingRepositoryTest {

    @Autowired
    private ReviewRestaurantRepository reviewRestaurantRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Test
    public void shouldSaveReviewRestaurant() {
        User user = new User();
        user.setUserName("tinywiz");
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantName("100 Degrees");
        userRepository.save(user);
        restaurantRepository.save(restaurant);
        ReviewRestaurantPK reviewRestaurantPK = new ReviewRestaurantPK(user.getId(), restaurant.getRestaurantId());
        ReviewRestaurant reviewRestaurant = new ReviewRestaurant();
        reviewRestaurant.setReviewRestaurantPK(reviewRestaurantPK);

        reviewRestaurantRepository.save(reviewRestaurant);
    }
}
