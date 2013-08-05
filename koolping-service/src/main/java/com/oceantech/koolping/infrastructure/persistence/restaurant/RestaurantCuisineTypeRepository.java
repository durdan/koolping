package com.oceantech.koolping.infrastructure.persistence.restaurant;

import com.oceantech.koolping.domain.model.restaurant.RestaurantCuisineType;
import org.springframework.data.repository.CrudRepository;

/**
 * Created with IntelliJ IDEA.
 * User: durdan
 * Date: 30/07/13
 * Time: 22:15
 * To change this template use File | Settings | File Templates.
 */
public interface RestaurantCuisineTypeRepository extends CrudRepository<RestaurantCuisineType,Long> {
}
