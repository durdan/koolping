package com.oceantech.koolping.repository;

import com.oceantech.koolping.domain.Item;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.EndResult;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Sanjoy Roy
 */
public class ItemRepositoryTest extends AbstractKoolPingRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void shouldSave(){
        Item item = new Item();
        item.setPlaceId("ABC");

        Item actual = itemRepository.save(item);

        assertThat(actual).isNotNull();
        assertThat(actual.getPlaceId()).isEqualTo("ABC");
    }

    @Test
    public void shouldFindOne(){
        Item item = new Item();
        Item savedItem = itemRepository.save(item);

        Item actual = itemRepository.findOne(savedItem.getId());

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(savedItem.getId());
    }

    @Test
    public void shouldFindAll(){
        Item item1 = new Item();
        itemRepository.save(item1);
        Item item2 = new Item();
        itemRepository.save(item2);

        EndResult<Item> items = itemRepository.findAll();
        assertThat(items.as(List.class).size()).isEqualTo(2);
    }


    @Test
    public void shouldFindByPlaceId(){
        Item item = new Item();
        item.setPlaceId("ABC");
        itemRepository.save(item);

        Item actual = itemRepository.findByPlaceId("ABC");

        assertThat(actual).isNotNull();
        assertThat(actual.getPlaceId()).isEqualTo("ABC");
    }

    @Test
    public void shouldDelete(){
        Item item = new Item();
        item.setPlaceId("ABC");
        Item savedItem = itemRepository.save(item);

        itemRepository.delete(savedItem);

        EndResult<Item> items = itemRepository.findAll();
        assertThat(items.as(List.class).size()).isEqualTo(0);
    }
}
