package com.oceantech.koolping.api.resource;

import org.junit.Test;
import org.springframework.hateoas.Link;

import java.util.Arrays;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Sanjoy Roy
 */
public class PersonResourceCollectionTest {

    @Test
    public void shouldConstructPersonResourceCollection() {
        PersonResourceCollection collection1 = new PersonResourceCollection();
        assertThat(collection1).isNotNull();

        PersonResourceCollection collection2 = new PersonResourceCollection(Arrays.asList(new PersonResource()), new Link("/test"));

        assertThat(collection2).isNotNull();
        assertThat(collection2.getContent().size()).isEqualTo(1);
        assertThat(collection2.getLinks().size()).isEqualTo(1);
    }
}
