package com.oceantech.koolping.repository;

import com.oceantech.koolping.domain.Person;
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
}
