package com.oceantech.koolping.domain;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Sanjoy Roy
 */
public class RateTest {

    @Test
    public void shouldSameRateBeSame(){
        Rate rate = new Rate();
        rate.setId(100L);
        rate.setPerson(getPerson(1000L));
        rate.setItem(getItem(2000L));

        assertThat(rate).isEqualTo(rate);
    }

    @Test
    public void shouldRateWithSameIdPersonAndItemBeSame(){
        Rate firstRate = new Rate();
        firstRate.setId(100L);
        firstRate.setPerson(getPerson(1000L));
        firstRate.setItem(getItem(2000L));
        Rate secondRate = new Rate();
        secondRate.setId(100L);
        secondRate.setPerson(getPerson(1000L));
        secondRate.setItem(getItem(2000L));

        assertThat(firstRate).isEqualTo(secondRate);
        assertThat(secondRate).isEqualTo(firstRate);
    }

    @Test
    public void shouldRateWithDifferentIdNotBeSame(){
        Rate firstRate = new Rate();
        firstRate.setId(100L);
        firstRate.setPerson(getPerson(1000L));
        firstRate.setItem(getItem(2000L));
        Rate secondRate = new Rate();
        secondRate.setId(200L);
        secondRate.setPerson(getPerson(1000L));
        secondRate.setItem(getItem(2000L));

        assertThat(firstRate).isNotEqualTo(secondRate);
        assertThat(secondRate).isNotEqualTo(firstRate);
    }

    @Test
    public void shouldRateFromDifferentPersonsNotBeSame(){
        Rate firstRate = new Rate();
        firstRate.setId(100L);
        firstRate.setPerson(getPerson(1000L));
        firstRate.setItem(getItem(2000L));
        Rate secondRate = new Rate();
        secondRate.setId(100L);
        secondRate.setPerson(getPerson(3000L));
        secondRate.setItem(getItem(2000L));

        assertThat(firstRate).isNotEqualTo(secondRate);
        assertThat(secondRate).isNotEqualTo(firstRate);
    }

    @Test
    public void shouldRateForDifferentItemsNotBeSame(){
        Rate firstRate = new Rate();
        firstRate.setId(100L);
        firstRate.setPerson(getPerson(1000L));
        firstRate.setItem(getItem(2000L));
        Rate secondRate = new Rate();
        secondRate.setId(100L);
        secondRate.setPerson(getPerson(1000L));
        secondRate.setItem(getItem(3000L));

        assertThat(firstRate).isNotEqualTo(secondRate);
        assertThat(secondRate).isNotEqualTo(firstRate);
    }

    @Test
    public void shouldPassTransitiveTest(){
        Rate firstRate = new Rate();
        firstRate.setId(100L);
        firstRate.setPerson(getPerson(1000L));
        firstRate.setItem(getItem(2000L));
        Rate secondRate = new Rate();
        secondRate.setId(100L);
        secondRate.setPerson(getPerson(1000L));
        secondRate.setItem(getItem(2000L));
        Rate thirdRate = new Rate();
        thirdRate.setId(100L);
        thirdRate.setPerson(getPerson(1000L));
        thirdRate.setItem(getItem(2000L));


        assertThat(firstRate).isEqualTo(secondRate);
        assertThat(secondRate).isEqualTo(thirdRate);
        assertThat(firstRate).isEqualTo(thirdRate);
    }

    @Test
    public void shouldRateMustNotBeEqualToNull(){
        Rate rate = new Rate();
        assertThat(rate).isNotEqualTo(null);
    }

    private Person getPerson(Long id){
        Person person = new Person();
        person.setId(id);
        return person;
    }

    private Item getItem(Long id){
        Item item = new Item();
        item.setId(id);
        return item;
    }
}
