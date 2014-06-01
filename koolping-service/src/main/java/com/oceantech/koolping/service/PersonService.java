package com.oceantech.koolping.service;

import com.oceantech.koolping.domain.Item;
import com.oceantech.koolping.domain.Person;

import java.util.List;

/**
 * @author Sanjoy Roy
 */
public interface PersonService {
    List<Person> findAll();
    Person findById(final Long id);
    Person findByUsername(final String username);
    Person findByFacebookId(final String facebookId);
    Person findByTwitterId(final String twitterId);
    Person findByGoogleplusId(final String googleplusId);
    Person create(final Person person);
    Person update(final Person person);
    void delete(final Person person);

    String findMyRating(final Person person, final Item item);

    Integer findMyFriendsRating(final Person person, final Item item, final String rating);
}
