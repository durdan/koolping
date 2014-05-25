package com.oceantech.koolping.repository;

import com.oceantech.koolping.domain.Category;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.EndResult;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Sanjoy Roy
 */
public class CategoryRepositoryTest extends AbstractKoolPingRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void shouldSave(){
        Category category = new Category();
        category.setName("cafe");

        Category actual = categoryRepository.save(category);

        assertThat(actual).isNotNull();
        assertThat(actual.getName()).isEqualTo("cafe");
    }

    @Test
    public void shouldFindOne(){
        Category category = new Category();
        Category savedCategory = categoryRepository.save(category);

        Category actual = categoryRepository.findOne(savedCategory.getId());

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(savedCategory.getId());
    }

    @Test
    public void shouldFindAll(){
        Category category1 = new Category();
        categoryRepository.save(category1);
        Category category2 = new Category();
        categoryRepository.save(category2);

        EndResult<Category> categories = categoryRepository.findAll();
        assertThat(categories.as(List.class).size()).isEqualTo(2);
    }


    @Test
    public void shouldFindByName(){
        Category category = new Category();
        category.setName("cafe");
        categoryRepository.save(category);

        Category actual = categoryRepository.findByName("cafe");

        assertThat(actual).isNotNull();
        assertThat(actual.getName()).isEqualTo("cafe");
    }

    @Test
    public void shouldDelete(){
        Category category = new Category();
        category.setName("cafe");
        Category savedCategory = categoryRepository.save(category);

        categoryRepository.delete(savedCategory);

        EndResult<Category> categories = categoryRepository.findAll();
        assertThat(categories.as(List.class).size()).isEqualTo(0);
    }
}

