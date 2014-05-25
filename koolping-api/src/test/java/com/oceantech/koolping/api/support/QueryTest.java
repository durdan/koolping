package com.oceantech.koolping.api.support;

import org.junit.Test;

import java.util.Arrays;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Sanjoy Roy
 */
public class QueryTest {

    @Test
    public void shouldConstructQuery() {
        Query query = new Query();
        query.setHref("href");
        query.setDisplay("display");
        query.setFields(Arrays.asList(new Query.Field("name")));

        assertThat(query).isNotNull();
        assertThat(query.getHref()).isEqualTo("href");
        assertThat(query.getDisplay()).isEqualTo("display");
        assertThat(query.getFields().size()).isEqualTo(1);
    }
}
