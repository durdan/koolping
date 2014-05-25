package com.oceantech.koolping.api.resource;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Sanjoy Roy
 */
public class CategoryFormTest {

    @Test
    public void shouldConstructCategoryForm(){
        CategoryForm form = new CategoryForm();
        form.setName("cafe");

        assertThat(form).isNotNull();
        assertThat(form.getName()).isEqualTo("cafe");
    }
}
