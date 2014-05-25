package com.oceantech.koolping.api.resource;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Sanjoy Roy
 */
public class ItemResourceTest {
    @Test
    public void shouldConstructItemResource() {
        ItemResource resource = new ItemResource();
        resource.setItemRef("urn:items:1");
        resource.setName("item");

        assertThat(resource).isNotNull();
        assertThat(resource.getItemRef()).isEqualTo("urn:items:1");
        assertThat(resource.getName()).isEqualTo("item");
    }
}
