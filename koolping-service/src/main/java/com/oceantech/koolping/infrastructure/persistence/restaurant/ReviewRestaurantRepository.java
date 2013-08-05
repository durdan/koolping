package com.oceantech.koolping.infrastructure.persistence.restaurant;

import com.oceantech.koolping.domain.model.restaurant.ReviewRestaurant;
import com.oceantech.koolping.domain.model.restaurant.ReviewRestaurantPK;
import org.springframework.data.repository.CrudRepository;

/**
 * Created with IntelliJ IDEA.
 * User: durdan
 * Date: 05/08/13
 * Time: 20:38
 * To change this template use File | Settings | File Templates.
 */

public interface ReviewRestaurantRepository extends CrudRepository<ReviewRestaurant,ReviewRestaurantPK> {
}
