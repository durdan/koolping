package com.oceantech.koolping.api.resource;

import org.junit.Test;
import org.springframework.hateoas.Link;

import java.util.Arrays;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Sanjoy Roy
 */
public class RatedItemResourceCollectionTest {

    @Test
    public void shouldConstructRatedItemResourceCollection() {
        RatedItemResourceCollection collection1 = new RatedItemResourceCollection();
        assertThat(collection1).isNotNull();

        RatedItemResourceCollection collection2 = new RatedItemResourceCollection(Arrays.asList(new RatedItemResource()), new Link("/test"));

        assertThat(collection2).isNotNull();
        assertThat(collection2.getContent().size()).isEqualTo(1);
        assertThat(collection2.getLinks().size()).isEqualTo(1);
    }
}
