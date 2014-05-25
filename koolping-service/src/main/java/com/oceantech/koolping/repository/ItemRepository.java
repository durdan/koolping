package com.oceantech.koolping.repository;

import com.oceantech.koolping.domain.Item;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Sanjoy Roy
 */
@Transactional(propagation = Propagation.MANDATORY, readOnly = false)
public interface ItemRepository extends GraphRepository<Item> {
    Item findByPlaceId(String placeId);
}
