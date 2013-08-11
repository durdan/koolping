package com.oceantech.koolping.infrastructure.persistence.restaurant;

import com.oceantech.koolping.domain.model.restaurant.RestaurantCuisineType;
import org.springframework.data.repository.CrudRepository;


public interface RestaurantCuisineTypeRepository extends CrudRepository<RestaurantCuisineType,Long> {
}
