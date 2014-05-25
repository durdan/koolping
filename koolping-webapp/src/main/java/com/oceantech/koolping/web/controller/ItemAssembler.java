package com.oceantech.koolping.web.controller;

import com.oceantech.koolping.api.ApplicationProtocol;
import com.oceantech.koolping.api.resource.ItemForm;
import com.oceantech.koolping.api.resource.ItemResource;
import com.oceantech.koolping.domain.Category;
import com.oceantech.koolping.domain.Item;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Sanjoy Roy
 */
public class ItemAssembler {

    public static List<ItemResource> toResources(Page<Item> items) {
        List<ItemResource> itemResources = new ArrayList<>();
        for (Item item : items) {
            itemResources.add(toResource(item));
        }
        return itemResources;
    }

    public static ItemResource toResource(Item item) {
        ItemResource resource = new ItemResource();
        resource.setItemRef("urn:items:" + item.getId());
        resource.setPlaceId(item.getPlaceId());

        resource.add(ControllerLinkBuilder.
                linkTo(ControllerLinkBuilder.
                        methodOn(ItemController.class).
                        getItem(item.getId()))
                .withSelfRel());

        resource.add(ControllerLinkBuilder.
                linkTo(ControllerLinkBuilder.
                        methodOn(ItemController.class).
                        needCategoryInfo(item.getId())).
                withRel(ApplicationProtocol.ADD_CATEGORY));

        if (!item.getCategories().isEmpty()) {
            Set<String> categories = new LinkedHashSet<>(item.getCategories().size());
            for (Category category : item.getCategories()) {
                categories.add(category.getName());
            }
            resource.setCategories(categories);
        }

        return resource;
    }

    public static Item toItem(ItemForm form) {
        Item item = new Item();
        item.setPlaceId(form.getPlaceId());
        return item;
    }
}
