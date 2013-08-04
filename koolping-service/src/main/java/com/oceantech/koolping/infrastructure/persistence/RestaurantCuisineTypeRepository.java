package com.oceantech.koolping.infrastructure.persistence;

import com.oceantech.koolping.domain.model.RestaurantCuisineType;
import org.springframework.data.repository.CrudRepository;


public interface RestaurantCuisineTypeRepository extends CrudRepository<RestaurantCuisineType,Long> {
}
