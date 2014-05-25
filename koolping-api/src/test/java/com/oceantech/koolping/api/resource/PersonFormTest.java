package com.oceantech.koolping.api.resource;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Sanjoy Roy
 */
public class PersonFormTest {

    @Test
    public void shouldConstructPersonForm(){
        PersonForm form = new PersonForm();
        form.setBirthDate("12/08/1978");
        form.setEmail("test@test.com");
        form.setFirstName("first");
        form.setLastName("last");
        form.setGender("male");
        form.setPassword("password");
        form.setUsername("username");

        assertThat(form).isNotNull();
        assertThat(form.getBirthDate()).isEqualTo("12/08/1978");
        assertThat(form.getEmail()).isEqualTo("test@test.com");
        assertThat(form.getFirstName()).isEqualTo("first");
        assertThat(form.getLastName()).isEqualTo("last");
        assertThat(form.getGender()).isEqualTo("male");
        assertThat(form.getPassword()).isEqualTo("password");
        assertThat(form.getUsername()).isEqualTo("username");
    }
}
