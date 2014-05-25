package com.oceantech.koolping.api.resource;

import org.junit.Test;
import org.springframework.hateoas.Link;

import java.util.Arrays;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Sanjoy Roy
 */
public class CategoryResourceCollectionTest {
    @Test
    public void shouldConstructCategoryResourceCollection() {
        CategoryResourceCollection collection1 = new CategoryResourceCollection();
        assertThat(collection1).isNotNull();

        CategoryResourceCollection collection2 = new CategoryResourceCollection(Arrays.asList(new CategoryResource()), new Link("/test"));

        assertThat(collection2).isNotNull();
        assertThat(collection2.getContent().size()).isEqualTo(1);
        assertThat(collection2.getLinks().size()).isEqualTo(1);
    }
}
