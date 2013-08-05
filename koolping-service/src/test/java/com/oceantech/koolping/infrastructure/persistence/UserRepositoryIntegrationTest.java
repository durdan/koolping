package com.oceantech.koolping.infrastructure.persistence;

import com.oceantech.koolping.AbstractKoolpingRepositoryTest;
import com.oceantech.koolping.domain.model.common.Country;
import com.oceantech.koolping.domain.model.identity.Address;
import com.oceantech.koolping.domain.model.identity.User;
import com.oceantech.koolping.domain.model.identity.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class UserRepositoryIntegrationTest extends AbstractKoolpingRepositoryTest {


    @Autowired
    private UserRepository repository;

    @Autowired
    private CountryRepository  countryRepository;

    @Test
    public void shouldSaveUser() {
        User savedUser = repository.save(new User());
        assertThat(savedUser.getId(), is(notNullValue()));
    }

    @Test
    public void shouldFindAllUsers() {
        createSomeUsers();

        Iterable<User> result = repository.findAll();

        assertThat(result, is(notNullValue()));
        assertTrue(result.iterator().hasNext());
    }

    @Test
    public void shouldFindByUsername() {
        createSomeUsers();

        Page<User> users = repository.findByUserName("timpre", new PageRequest(0, 2));

        assertThat(users.getContent().size(), is(1));
        assertFalse(users.hasPreviousPage());
    }

    @Test
    public void shouldFindByUserId() {
        createSomeUsers();
        UserId userIdToLookFor = new UserId("uid1");

        Page<User> users = repository.findByUserId(userIdToLookFor, new PageRequest(0, 2));

        assertThat(users.getContent().size(), is(1));
        assertFalse(users.hasPreviousPage());
    }

    @Test
    public void shouldFindById() {
        User user = repository.save(createUser("timpre", "uid1", "Tim", "Prentice"));

        User savedUser = repository.findOne(user.getId());

        assertThat(savedUser.getId(), is(notNullValue()));
        assertThat(savedUser.getFirstName(), is("Tim"));
        assertThat(savedUser.getLastName(), is("Prentice"));
    }

    // Helper Methods

    private void createSomeUsers() {
        createUser("johsmi", "uid1", "John", "Smith");
        createUser("timpre", "uid2", "Tim", "Prentice");
    }

    private User createUser(String username, String userId, String firstname, String lastname) {
        User user = new User();
        Country country = new Country("UK", "United Kingdom");
        countryRepository.save(country);
        Address address = new Address("Pinner Lane", "London", "HA5 4RR", country);
        UserId uid = new UserId(userId);

        user.setUserId(uid);
        user.setUserName(username);
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setAddress(address);
        return repository.save(user);
    }
}
