package com.oceantech.koolping.domain;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Sanjoy Roy
 */
public class PersonTest {

    @Test
    public void shouldSamePersonBeSame(){
        Person person = new Person();
        person.setId(100L);

        assertThat(person).isEqualTo(person);
    }

    @Test
    public void shouldPersonWithSameIdBeSame(){
        Person firstPerson = new Person();
        firstPerson.setId(100L);
        Person secondPerson = new Person();
        secondPerson.setId(100L);

        assertThat(firstPerson).isEqualTo(secondPerson);
        assertThat(secondPerson).isEqualTo(firstPerson);
    }

    @Test
    public void shouldPersonWithDifferentIdNotBeSame(){
        Person firstPerson = new Person();
        firstPerson.setId(100L);
        Person secondPerson = new Person();
        secondPerson.setId(200L);

        assertThat(firstPerson).isNotEqualTo(secondPerson);
        assertThat(secondPerson).isNotEqualTo(firstPerson);
    }

    @Test
    public void shouldPassTransitiveTest(){
        Person firstPerson = new Person();
        firstPerson.setId(100L);
        Person secondPerson = new Person();
        secondPerson.setId(100L);
        Person thirdPerson = new Person();
        thirdPerson.setId(100L);


        assertThat(firstPerson).isEqualTo(secondPerson);
        assertThat(secondPerson).isEqualTo(thirdPerson);
        assertThat(firstPerson).isEqualTo(thirdPerson);
    }

    @Test
    public void shouldPersonMustNotBeEqualToNull(){
        Person person = new Person();
        assertThat(person).isNotEqualTo(null);
    }
}
