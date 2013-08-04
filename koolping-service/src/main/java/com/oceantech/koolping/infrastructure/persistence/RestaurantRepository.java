package com.oceantech.koolping.infrastructure.persistence;

import com.oceantech.koolping.domain.model.restaurant.Restaurant;
import org.springframework.data.repository.CrudRepository;


public interface RestaurantRepository extends CrudRepository<Restaurant,Long>  {
}
