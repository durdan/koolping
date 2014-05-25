package com.oceantech.koolping.api.resource;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Sanjoy Roy
 */
public class ChoiceResourceTest {
    @Test
    public void shouldConstructChoiceResource() {
        ChoiceResource resource = new ChoiceResource();
        resource.setName("choice");

        assertThat(resource).isNotNull();
        assertThat(resource.getName()).isEqualTo("choice");
    }
}
