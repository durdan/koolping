package com.oceantech.koolping.web.controller;

import com.oceantech.koolping.api.resource.CategoryForm;
import com.oceantech.koolping.api.resource.CategoryResource;
import com.oceantech.koolping.domain.Category;

import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sanjoy Roy
 */
public class CategoryAssembler {

    public static List<CategoryResource> toResources(List<Category> categories){
        List<CategoryResource> categoryResources = new ArrayList<>();
        for(Category category : categories){
            categoryResources.add(toResource(category));
        }
        return categoryResources;
    }

    public static CategoryResource toResource(Category category){
        CategoryResource resource = new CategoryResource();
        resource.setCategoryRef("urn:categories:" + category.getId());
        resource.setName(category.getName());

        resource.add(ControllerLinkBuilder.
                linkTo(ControllerLinkBuilder.
                        methodOn(CategoryController.class).
                        getCategory(category.getId()))
                .withSelfRel());
        return resource;
    }

    public static Category toCategory(CategoryForm form){
        Category category = new Category();
        category.setName(form.getName());
        return category;
    }
}
