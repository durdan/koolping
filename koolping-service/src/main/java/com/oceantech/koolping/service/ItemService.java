package com.oceantech.koolping.service;

import com.oceantech.koolping.domain.Item;
import org.springframework.data.domain.Page;

/**
 * @author Sanjoy Roy
 */

public interface ItemService {
    Page<Item> findAll();
    Item findById(final Long id);
    Item create(final Item item);
    Item update(final Item item);
    void delete(final Item item);
}
