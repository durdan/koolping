package com.oceantech.koolping.api.resource;

import org.junit.Test;
import org.springframework.hateoas.Link;

import java.util.Arrays;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Sanjoy Roy
 */
public class ItemResourceCollectionTest {
    @Test
    public void shouldConstructItemResourceCollection() {
        ItemResourceCollection collection1 = new ItemResourceCollection();
        assertThat(collection1).isNotNull();

        ItemResourceCollection collection2 = new ItemResourceCollection(Arrays.asList(new ItemResource()), new Link("/test"));

        assertThat(collection2).isNotNull();
        assertThat(collection2.getContent().size()).isEqualTo(1);
        assertThat(collection2.getLinks().size()).isEqualTo(1);
    }
}
