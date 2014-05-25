package com.oceantech.koolping.web.controller;

import com.oceantech.koolping.annotation.BoundaryController;
import com.oceantech.koolping.api.ApplicationProtocol;
import com.oceantech.koolping.api.resource.*;
import com.oceantech.koolping.domain.Category;
import com.oceantech.koolping.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import static com.oceantech.koolping.web.controller.CategoryAssembler.toCategory;
import static com.oceantech.koolping.web.controller.CategoryAssembler.toResource;
import static com.oceantech.koolping.web.controller.CategoryAssembler.toResources;

/**
 * Controller for managing categories
 *
 * @author Sanjoy Roy
 */

@RequestMapping(value = "/categories", produces = MediaType.APPLICATION_JSON_VALUE)
@BoundaryController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<CategoryResourceCollection> getCategories(){

        List<Category> categories = categoryService.findAll();

        CategoryResourceCollection collection = new CategoryResourceCollection(toResources(categories));

        collection.add(ControllerLinkBuilder.
                linkTo(ControllerLinkBuilder.
                        methodOn(CategoryController.class).
                        getCategories()).
                withSelfRel());

        collection.add(ControllerLinkBuilder.
                linkTo(ControllerLinkBuilder.
                        methodOn(CategoryController.class).
                        getCategoryForm()).
                withRel(ApplicationProtocol.CATEGORY_FORM));

        collection.setPageSize(1);
        collection.setReturnedItems(categories.size());
        collection.setTotalItems(categories.size());
        collection.setTotalPages(1);

        return new ResponseEntity<>(collection, HttpStatus.OK);
    }

    @RequestMapping(value = "/{categoryId}", method = RequestMethod.GET)
    public HttpEntity<CategoryResource> getCategory(@PathVariable("categoryId")Long categoryId){

        Category category = categoryService.findById(categoryId);
        CategoryResource resource = toResource(category);

        resource.add(ControllerLinkBuilder.
                linkTo(ControllerLinkBuilder.
                        methodOn(CategoryController.class).
                        deleteCategory(category.getId())).
                withRel(ApplicationProtocol.DELETE_CATEGORY));

        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public HttpEntity<CategoryForm> getCategoryForm(){
        CategoryForm form = new CategoryForm();
        form.add(ControllerLinkBuilder.
                linkTo(ControllerLinkBuilder.
                        methodOn(CategoryController.class).
                        createCategory(form)).
                withRel(ApplicationProtocol.CREATE_CATEGORY));
        return new ResponseEntity<>(form, HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<CategoryResource> createCategory(@RequestBody CategoryForm form){
        Category category = categoryService.create(toCategory(form));
        CategoryResource resource = toResource(category);
        return new ResponseEntity<>(resource, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{categoryId}", method = RequestMethod.DELETE)
    public HttpEntity deleteCategory(@PathVariable("categoryId")Long categoryId){
        Category category = categoryService.findById(categoryId);
        categoryService.delete(category);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
