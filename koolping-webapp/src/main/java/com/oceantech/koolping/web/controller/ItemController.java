package com.oceantech.koolping.web.controller;

import com.oceantech.koolping.annotation.BoundaryController;
import com.oceantech.koolping.api.ApplicationProtocol;
import com.oceantech.koolping.api.resource.*;
import com.oceantech.koolping.domain.Category;
import com.oceantech.koolping.domain.Item;
import com.oceantech.koolping.service.CategoryService;
import com.oceantech.koolping.service.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

import static com.oceantech.koolping.web.controller.ItemAssembler.toItem;
import static com.oceantech.koolping.web.controller.ItemAssembler.toResource;
import static com.oceantech.koolping.web.controller.ItemAssembler.toResources;

/**
 * Controller for managing items
 *
 * @author Sanjoy Roy
 */

@RequestMapping(value = "/items", produces = MediaType.APPLICATION_JSON_VALUE)
@BoundaryController
public class ItemController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<ItemResourceCollection> getItems(){

        Page<Item> items = itemService.findAll();

        ItemResourceCollection collection = new ItemResourceCollection(toResources(items));

        collection.add(ControllerLinkBuilder.
                linkTo(ControllerLinkBuilder.
                        methodOn(ItemController.class).
                        getItemForm()).
                withRel(ApplicationProtocol.ITEM_FORM));

        collection.setPageSize(items.getSize());
        collection.setReturnedItems(items.getNumberOfElements());
        collection.setTotalItems(items.getTotalElements());
        collection.setTotalPages(items.getTotalPages());

        return new ResponseEntity<>(collection, HttpStatus.OK);
    }

    @RequestMapping(value = "/{itemId}", method = RequestMethod.GET)
    public HttpEntity<ItemResource> getItem(@PathVariable("itemId")Long itemId){

        Item item = itemService.findById(itemId);
        ItemResource resource = toResource(item);

        resource.add(ControllerLinkBuilder.
                linkTo(ControllerLinkBuilder.
                        methodOn(ItemController.class).
                        deleteItem(item.getId())).
                withRel(ApplicationProtocol.DELETE_ITEM));

        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public HttpEntity<ItemForm> getItemForm(){
        ItemForm form = new ItemForm();
        form.add(ControllerLinkBuilder.
                linkTo(ControllerLinkBuilder.
                        methodOn(ItemController.class).
                        createItem(form)).
                withRel(ApplicationProtocol.CREATE_ITEM));
        return new ResponseEntity<>(form, HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<ItemResource> createItem(@RequestBody ItemForm form){
        Item item = itemService.create(toItem(form));
        ItemResource resource = toResource(item);
        return new ResponseEntity<>(resource, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{itemId}/categories", method = RequestMethod.GET)
    public HttpEntity<SelectorResource> needCategoryInfo(@PathVariable("itemId")Long itemId){

        Item item = itemService.findById(itemId);

        List<Category> categories = categoryService.findAll();
        SelectorResource resource = new SelectorResource();
        List<ChoiceResource> choices = new ArrayList<>();
        for(Category category : categories){
            ChoiceResource choice = new ChoiceResource();
            choice.setName(category.getName());
            if(item.getCategories().contains(category)){
                choice.add(ControllerLinkBuilder.
                        linkTo(ControllerLinkBuilder.
                                methodOn(ItemController.class).
                                deleteCategory(item.getId(), category.getId())).
                        withRel(ApplicationProtocol.UNSELECT_ACTION));
            } else {
                choice.add(ControllerLinkBuilder.
                        linkTo(ControllerLinkBuilder.
                                methodOn(ItemController.class).
                                addCategory(item.getId(), category.getId())).
                        withRel(ApplicationProtocol.SELECT_ACTION));
            }
            choices.add(choice);
        }
        resource.setChoiceResources(choices);

        resource.add(ControllerLinkBuilder.
                linkTo(ControllerLinkBuilder.
                        methodOn(ItemController.class).
                        needCategoryInfo(item.getId())).
                withSelfRel());

        resource.add(ControllerLinkBuilder.
                linkTo(ControllerLinkBuilder.
                        methodOn(ItemController.class).
                        getItems()).
                withRel(ApplicationProtocol.ITEMS));

        resource.add(ControllerLinkBuilder.
                linkTo(ControllerLinkBuilder.
                        methodOn(ItemController.class).
                        getItem(item.getId())).
                withRel(ApplicationProtocol.ITEM));

        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @RequestMapping(value = "/{itemId}/categories/{categoryId}", method = RequestMethod.PUT)
    public HttpEntity<SelectorResource> addCategory(@PathVariable("itemId")Long itemId, @PathVariable("categoryId")Long categoryId){

        Item item = itemService.findById(itemId);
        Category category = categoryService.findById(categoryId);
        item.getCategories().add(category);
        Item updatedItem = itemService.update(item);
        return needCategoryInfo(updatedItem.getId());
    }

    @RequestMapping(value = "/{itemId}/categories/{categoryId}", method = RequestMethod.DELETE)
    public HttpEntity<SelectorResource> deleteCategory(@PathVariable("itemId")Long itemId, @PathVariable("categoryId")Long categoryId){

        Item item = itemService.findById(itemId);
        Category category = categoryService.findById(categoryId);

        if(item.getCategories().contains(category)){
            item.getCategories().remove(category);
            Item updatedItem = itemService.update(item);
            return needCategoryInfo(updatedItem.getId());
        }
        return needCategoryInfo(itemId);
    }


    @RequestMapping(value = "/{itemId}", method = RequestMethod.DELETE)
    public HttpEntity deleteItem(@PathVariable("itemId")Long itemId){
        Item item = itemService.findById(itemId);
        itemService.delete(item);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
