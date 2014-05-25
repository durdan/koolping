package com.oceantech.koolping.domain;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Sanjoy Roy
 */
public class CategoryTest {

    @Test
    public void shouldSameCategoryBeSame(){
        Category category = new Category();
        category.setId(100L);

        assertThat(category).isEqualTo(category);
    }

    @Test
    public void shouldCategoryWithSameIdBeSame(){
        Category firstCategory = new Category();
        firstCategory.setId(100L);
        Category secondCategory = new Category();
        secondCategory.setId(100L);

        assertThat(firstCategory).isEqualTo(secondCategory);
        assertThat(secondCategory).isEqualTo(firstCategory);
    }

    @Test
    public void shouldCategoryWithDifferentIdNotBeSame(){
        Category firstCategory = new Category();
        firstCategory.setId(100L);
        Category secondCategory = new Category();
        secondCategory.setId(200L);

        assertThat(firstCategory).isNotEqualTo(secondCategory);
        assertThat(secondCategory).isNotEqualTo(firstCategory);
    }

    @Test
    public void shouldPassTransitiveTest(){
        Category firstCategory = new Category();
        firstCategory.setId(100L);
        Category secondCategory = new Category();
        secondCategory.setId(100L);
        Category thirdCategory = new Category();
        thirdCategory.setId(100L);


        assertThat(firstCategory).isEqualTo(secondCategory);
        assertThat(secondCategory).isEqualTo(thirdCategory);
        assertThat(firstCategory).isEqualTo(thirdCategory);
    }

    @Test
    public void shouldCategoryMustNotBeEqualToNull(){
        Category category = new Category();
        assertThat(category).isNotEqualTo(null);
    }
}
