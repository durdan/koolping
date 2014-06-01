package com.oceantech.koolping.repository;

import com.oceantech.koolping.domain.Item;
import com.oceantech.koolping.domain.Person;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Sanjoy Roy
 */
@Transactional(propagation = Propagation.MANDATORY, readOnly = false)
public interface PersonRepository  extends GraphRepository<Person> {
    Person findByUsername(String username);
    Person findByFacebookId(String facebookId);
    Person findByTwitterId(String twitterId);
    Person findByGoogleplusId(String googleplusId);

    @Query("start person=node({0}), item=node({1}) match (person)-[r:RATED]->(item) return r.rating")
    String findMyRating(Person person, Item item);

    @Query("start person=node({0}), item=node({1}) match (person)-[:FRIEND]->()-[r:RATED]->(item) where r.rating=({2}) return count(r)")
    Integer findMyFriendsRating(Person person, Item item, String rating);
}
