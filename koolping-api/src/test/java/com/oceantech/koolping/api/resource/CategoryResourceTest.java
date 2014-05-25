package com.oceantech.koolping.api.resource;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Sanjoy Roy
 */
public class CategoryResourceTest {

    @Test
    public void shouldConstructCategoryResource() {
        CategoryResource resource = new CategoryResource();
        resource.setCategoryRef("urn:categories:1");
        resource.setName("cafe");


        assertThat(resource).isNotNull();
        assertThat(resource.getCategoryRef()).isEqualTo("urn:categories:1");
        assertThat(resource.getName()).isEqualTo("cafe");
    }
}
