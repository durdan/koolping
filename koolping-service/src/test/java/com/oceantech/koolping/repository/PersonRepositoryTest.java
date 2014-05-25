package com.oceantech.koolping.repository;

import com.oceantech.koolping.domain.Person;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.EndResult;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Sanjoy Roy
 */
public class PersonRepositoryTest extends AbstractKoolPingRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void shouldSave(){
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Smith");
        person.setUsername("username");
        person.setPassword("password");
        person.setBirthDate(new Date());
        person.setEmail("john.smith@personal.com");

        Person actual = personRepository.save(person);

        assertThat(actual).isNotNull();
        assertThat(actual.getFirstName()).isEqualTo("John");
        assertThat(actual.getLastName()).isEqualTo("Smith");
        assertThat(actual.getUsername()).isEqualTo("username");
        assertThat(actual.getPassword()).isEqualTo("password");
        assertThat(actual.getBirthDate()).isNotNull();
        assertThat(actual.getEmail()).isEqualTo("john.smith@personal.com");
    }

    @Test
    public void shouldFindAll(){
        Person person1 = new Person();
        personRepository.save(person1);
        Person person2 = new Person();
        personRepository.save(person2);

        EndResult<Person> persons = personRepository.findAll();
        assertThat(persons.as(List.class).size()).isEqualTo(2);
    }

    @Test
    public void shouldFindOne(){
        Person person = new Person();
        Person savedPerson = personRepository.save(person);

        Person actual = personRepository.findOne(savedPerson.getId());

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(savedPerson.getId());
    }

    @Test
    public void shouldFindByUsername(){
        Person person = new Person();
        person.setFirstName("Vijay");
        person.setLastName("Sharma");
        person.setUsername("vijaysharma");
        person.setPassword("password");
        person.setBirthDate(new Date());
        person.setEmail("vijay.sharma@personal.com");
        personRepository.save(person);

        Person actual = personRepository.findByUsername("vijaysharma");

        assertThat(actual).isNotNull();
        assertThat(actual.getFirstName()).isEqualTo("Vijay");
        assertThat(actual.getLastName()).isEqualTo("Sharma");
        assertThat(actual.getUsername()).isEqualTo("vijaysharma");
        assertThat(actual.getPassword()).isEqualTo("password");
        assertThat(actual.getBirthDate()).isNotNull();
        assertThat(actual.getEmail()).isEqualTo("vijay.sharma@personal.com");
    }

    @Test
    public void shouldFindByFacebookId(){
        Person person = new Person();
        person.setFacebookId("asdaseeqrewwr232fasd");
        personRepository.save(person);

        Person actual = personRepository.findByFacebookId("asdaseeqrewwr232fasd");

        assertThat(actual).isNotNull();
        assertThat(actual.getFacebookId()).isEqualTo("asdaseeqrewwr232fasd");
    }

    @Test
    public void shouldFindByTwitterId(){
        Person person = new Person();
        person.setTwitterId("asdaseeqrewwr232fasd");
        personRepository.save(person);

        Person actual = personRepository.findByTwitterId("asdaseeqrewwr232fasd");

        assertThat(actual).isNotNull();
        assertThat(actual.getTwitterId()).isEqualTo("asdaseeqrewwr232fasd");
    }

    @Test
    public void shouldFindByGoogleplusId(){
        Person person = new Person();
        person.setGoogleplusId("asdaseeqrewwr232fasd");
        personRepository.save(person);

        Person actual = personRepository.findByGoogleplusId("asdaseeqrewwr232fasd");

        assertThat(actual).isNotNull();
        assertThat(actual.getGoogleplusId()).isEqualTo("asdaseeqrewwr232fasd");
    }

    @Test
    public void shouldSaveFriend(){
        Person smith = new Person();
        smith.setFirstName("John");
        Person savedSmith = personRepository.save(smith);
        Person vijay = new Person();
        vijay.setFirstName("Vijay");
        Set<Person> vijayFriends = new HashSet<>();
        vijayFriends.add(smith);
        vijay.setFriends(vijayFriends);

        Person actual = personRepository.save(vijay);

        assertThat(actual).isNotNull();
        assertThat(actual.getFriends().size()).isEqualTo(1);
        assertThat(savedSmith).isNotNull();
    }

    @Test
    public void shouldDelete(){
        Person person = new Person();
        Person savedPerson = personRepository.save(person);

        personRepository.delete(savedPerson);

        EndResult<Person> persons = personRepository.findAll();
        assertThat(persons.as(List.class).size()).isEqualTo(0);
    }
}
