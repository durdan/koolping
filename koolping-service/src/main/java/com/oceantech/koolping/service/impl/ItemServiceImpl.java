package com.oceantech.koolping.service.impl;

import com.oceantech.koolping.annotation.ControlService;
import com.oceantech.koolping.domain.Item;
import com.oceantech.koolping.exception.IllegalKoolPingAction;
import com.oceantech.koolping.repository.ItemRepository;
import com.oceantech.koolping.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.domain.Page;

/**
 * @author Sanjoy Roy
 */

@ControlService
public class ItemServiceImpl implements ItemService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemServiceImpl.class);

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Page<Item> findAll() {
        return itemRepository.findAll().as(Page.class);
    }

    @Override
    public Item findById(final Long id) {
        Item item = itemRepository.findOne(id);
        if(item == null){
            throw new DataRetrievalFailureException("Id [ "+ id +" ] id not valid");
        }
        return item;
    }

    @Override
    public Item findByPlaceId(String placeId) {
        return itemRepository.findByPlaceId(placeId);
    }

    @Override
    public Item create(final Item item) {
        return save(item);
    }

    @Override
    public Item update(final Item item) {
        return save(item);
    }

    private Item save(final Item item) {
        Item exitedItem = itemRepository.findByPlaceId(item.getPlaceId().trim());
        if(exitedItem == null){
            Item savedItem = itemRepository.save(item);
            LOGGER.info("Item is created or updated {}", savedItem);
            return savedItem;
        } else {
            throw new IllegalKoolPingAction("Item with placeId [ "+item.getPlaceId()+" ] exists.");
        }
    }

    @Override
    public void delete(final Item item) {
        itemRepository.delete(item);
    }
}
