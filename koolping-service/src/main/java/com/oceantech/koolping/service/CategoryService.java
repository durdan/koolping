package com.oceantech.koolping.service;

import com.oceantech.koolping.domain.Category;

import java.util.List;

/**
 * @author Sanjoy Roy
 */
public interface CategoryService {
    List<Category> findAll();
    Category findById(final Long id);
    Category create(final Category category);
    void delete(final Category category);
}
