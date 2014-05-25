package com.oceantech.koolping.domain;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Sanjoy Roy
 */
public class ItemTest {
    @Test
    public void shouldSameItemBeSame(){
        Item item = new Item();
        item.setId(100L);

        assertThat(item).isEqualTo(item);
    }

    @Test
    public void shouldItemWithSameIdBeSame(){
        Item firstItem = new Item();
        firstItem.setId(100L);
        Item secondItem = new Item();
        secondItem.setId(100L);

        assertThat(firstItem).isEqualTo(secondItem);
        assertThat(secondItem).isEqualTo(firstItem);
    }

    @Test
    public void shouldItemWithDifferentIdNotBeSame(){
        Item firstItem = new Item();
        firstItem.setId(100L);
        Item secondItem = new Item();
        secondItem.setId(200L);

        assertThat(firstItem).isNotEqualTo(secondItem);
        assertThat(secondItem).isNotEqualTo(firstItem);
    }

    @Test
    public void shouldPassTransitiveTest(){
        Item firstItem = new Item();
        firstItem.setId(100L);
        Item secondItem = new Item();
        secondItem.setId(100L);
        Item thirdItem = new Item();
        thirdItem.setId(100L);


        assertThat(firstItem).isEqualTo(secondItem);
        assertThat(secondItem).isEqualTo(thirdItem);
        assertThat(firstItem).isEqualTo(thirdItem);
    }

    @Test
    public void shouldItemMustNotBeEqualToNull(){
        Item item = new Item();
        assertThat(item).isNotEqualTo(null);
    }

}
