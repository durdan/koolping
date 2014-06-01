package com.oceantech.koolping.repository;

import com.oceantech.koolping.domain.Item;
import com.oceantech.koolping.domain.Person;
import com.oceantech.koolping.domain.Rate;
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
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private RateRepository rateRepository;


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

    @Test
    public void shouldFindMyRating() {
        Person person = new Person();
        person = personRepository.save(person);
        Item item = new Item();
        item = itemRepository.save(item);
        Rate rate = new Rate();
        rate.setPerson(person);
        rate.setItem(item);
        rate.setRating("green");
        rate = rateRepository.save(rate);
        Set<Rate> ratings = new HashSet<>(1);
        ratings.add(rate);
        person.setRatings(ratings);
        personRepository.save(person);

        String actual = personRepository.findMyRating(person, item);

        assertThat(actual).isEqualTo("green");
    }

    @Test
    public void shouldMyFriendTotalRating() {

        Item item = new Item();
        item = itemRepository.save(item);

        Person friend1 = new Person();
        friend1 = personRepository.save(friend1);
        Rate ratedByFriend1 = new Rate();
        ratedByFriend1.setPerson(friend1);
        ratedByFriend1.setItem(item);
        ratedByFriend1.setRating("green");
        ratedByFriend1 = rateRepository.save(ratedByFriend1);
        Set<Rate> ratingsByFriend1 = new HashSet<>(1);
        ratingsByFriend1.add(ratedByFriend1);
        friend1.setRatings(ratingsByFriend1);
        friend1 = personRepository.save(friend1);

        Person friend2 = new Person();
        friend2 = personRepository.save(friend2);
        Rate ratedByFriend2 = new Rate();
        ratedByFriend2.setPerson(friend2);
        ratedByFriend2.setItem(item);
        ratedByFriend2.setRating("red");
        ratedByFriend2 = rateRepository.save(ratedByFriend2);
        Set<Rate> ratingsByFriend2 = new HashSet<>(1);
        ratingsByFriend2.add(ratedByFriend2);
        friend2.setRatings(ratingsByFriend2);
        friend2 = personRepository.save(friend2);

        Person friend3 = new Person();
        friend3 = personRepository.save(friend3);
        Rate ratedByFriend3 = new Rate();
        ratedByFriend3.setPerson(friend3);
        ratedByFriend3.setItem(item);
        ratedByFriend3.setRating("green");
        ratedByFriend3 = rateRepository.save(ratedByFriend3);
        Set<Rate> ratingsByFriend3 = new HashSet<>(1);
        ratingsByFriend3.add(ratedByFriend3);
        friend3.setRatings(ratingsByFriend3);
        friend3 = personRepository.save(friend3);

        Person me = new Person();
        Set<Person> friends = new HashSet<>(3);
        friends.add(friend1);
        friends.add(friend2);
        friends.add(friend3);
        me.setFriends(friends);
        me = personRepository.save(me);

        int totalGreenRating = personRepository.findMyFriendsRating(me, item, "green");
        int totalRedRating = personRepository.findMyFriendsRating(me, item, "red");

        assertThat(totalGreenRating).isEqualTo(2);
        assertThat(totalRedRating).isEqualTo(1);
    }

}
