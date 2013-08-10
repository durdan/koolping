package com.oceantech.koolping.infrastructure.persistence.restaurant;

import com.oceantech.koolping.domain.model.restaurant.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 * Created with IntelliJ IDEA.
 * User: durdan
 * Date: 30/07/13
 * Time: 22:11
 * To change this template use File | Settings | File Templates.
 */
public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {

    Page<Restaurant> findByRestaurantName(String restaurantName, Pageable pageable);

    Page<Restaurant> findByRestaurantId(Long restaurantId, Pageable pageable);

    Restaurant findByRestaurantName(String restaurantName);

    Page<Restaurant> findAll(Pageable pageable);

    Page<Restaurant> findByRestaurantNameLike(String restaurantName, Pageable pageable);



//    @Query("select u from Restaurant u where u.role.role = :role")
//    Page<Restaurant> findByRole(@Param("role") Integer role, Pageable pageable);
}
