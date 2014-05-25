package com.oceantech.koolping.api.resource;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Sanjoy Roy
 */
public class PersonResourceTest {

    @Test
    public void shouldConstructPersonResource() {
        PersonResource resource = new PersonResource();
        resource.setPersonRef("urn:persons:1");
        resource.setBirthDate("12/08/1978");
        resource.setEmail("test@test.com");
        resource.setFirstName("first");
        resource.setLastName("last");
        resource.setGender("male");
        resource.setUsername("username");
        resource.setFacebookId("facebookId");
        resource.setTwitterId("twitterId");
        resource.setGoogleplusId("googleplusId");

        assertThat(resource).isNotNull();
        assertThat(resource.getPersonRef()).isEqualTo("urn:persons:1");
        assertThat(resource.getBirthDate()).isEqualTo("12/08/1978");
        assertThat(resource.getEmail()).isEqualTo("test@test.com");
        assertThat(resource.getFirstName()).isEqualTo("first");
        assertThat(resource.getLastName()).isEqualTo("last");
        assertThat(resource.getGender()).isEqualTo("male");
        assertThat(resource.getUsername()).isEqualTo("username");
        assertThat(resource.getFacebookId()).isEqualTo("facebookId");
        assertThat(resource.getTwitterId()).isEqualTo("twitterId");
        assertThat(resource.getGoogleplusId()).isEqualTo("googleplusId");
    }
}
