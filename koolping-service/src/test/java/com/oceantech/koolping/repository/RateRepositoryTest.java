package com.oceantech.koolping.repository;

import com.oceantech.koolping.domain.Item;
import com.oceantech.koolping.domain.Person;
import com.oceantech.koolping.domain.Rate;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.EndResult;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Sanjoy Roy
 */
public class RateRepositoryTest extends AbstractKoolPingRepositoryTest {

    @Autowired
    private RateRepository rateRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ItemRepository itemRepository;

    private Person person;
    private Item item;

    @Test
    public void shouldSave(){
        createPerson();
        createItem();
        Rate rate = new Rate();
        rate.setPerson(person);
        rate.setItem(item);
        rate.setRating("green");

        Rate actual = rateRepository.save(rate);

        assertThat(actual).isNotNull();
        assertThat(actual.getPerson()).isNotNull();
        assertThat(actual.getItem()).isNotNull();
        assertThat(actual.getRating()).isEqualTo("green");
    }

    @Test
    public void shouldFindOne(){
        createPerson();
        createItem();
        Rate rate = new Rate();
        rate.setPerson(person);
        rate.setItem(item);
        rate.setRating("green");
        Rate savedRate = rateRepository.save(rate);

        Rate actual = rateRepository.findOne(savedRate.getId());

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(savedRate.getId());
    }

    @Test
    public void shouldFindAll(){
        createPerson();
        createItem();
        Rate rate1 = new Rate();
        rate1.setPerson(person);
        rate1.setItem(item);
        rate1.setRating("green");
        rateRepository.save(rate1);
        Rate rate2 = new Rate();
        rate2.setPerson(person);
        rate2.setItem(item);
        rate2.setRating("red");
        rateRepository.save(rate2);

        EndResult<Rate> rates = rateRepository.findAll();
        assertThat(rates.as(List.class).size()).isEqualTo(2);
    }

    @Test
    public void shouldDelete(){
        createPerson();
        createItem();
        Rate rate = new Rate();
        rate.setPerson(person);
        rate.setItem(item);
        rate.setRating("green");
        Rate savedRate = rateRepository.save(rate);

        rateRepository.delete(savedRate);

        EndResult<Rate> categories = rateRepository.findAll();
        assertThat(categories.as(List.class).size()).isEqualTo(0);
    }

    //Helpers
    private void createPerson(){
        person = personRepository.save(new Person());
    }

    private void createItem(){
        item = itemRepository.save(new Item());
    }

}
