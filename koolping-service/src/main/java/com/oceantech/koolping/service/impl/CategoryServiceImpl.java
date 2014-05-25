package com.oceantech.koolping.service.impl;

import com.oceantech.koolping.annotation.ControlService;
import com.oceantech.koolping.domain.Category;
import com.oceantech.koolping.exception.IllegalKoolPingAction;
import com.oceantech.koolping.repository.CategoryRepository;
import com.oceantech.koolping.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;

import java.util.List;

/**
 * @author Sanjoy Roy
 */

@ControlService
public class CategoryServiceImpl implements CategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll().as(List.class);
    }

    @Override
    public Category findById(Long id) {
        Category category = categoryRepository.findOne(id);
        if(category == null){
            throw new DataRetrievalFailureException("Id [ "+ id +" ] id not valid");
        }
        return category;
    }

    @Override
    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public Category create(Category category) {
        Category exitedCategory = categoryRepository.findByName(category.getName().trim());
        if(exitedCategory == null){
            Category savedCategory = categoryRepository.save(category);
            LOGGER.info("Category is created {}", savedCategory);
            return savedCategory;
        } else {
            throw new IllegalKoolPingAction("Category with name [ "+category.getName()+" ] exists.");
        }
    }

    @Override
    public void delete(Category category) {
        categoryRepository.delete(category);
    }
}
