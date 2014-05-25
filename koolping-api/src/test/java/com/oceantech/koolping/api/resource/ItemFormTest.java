package com.oceantech.koolping.api.resource;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Sanjoy Roy
 */
public class ItemFormTest {
    @Test
    public void shouldConstructItemForm(){
        ItemForm form = new ItemForm();
        form.setPlaceId("ABC");

        assertThat(form).isNotNull();
        assertThat(form.getPlaceId()).isEqualTo("ABC");
    }
}
