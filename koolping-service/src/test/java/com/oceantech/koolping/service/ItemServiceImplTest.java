package com.oceantech.koolping.service;

import com.oceantech.koolping.domain.Item;
import com.oceantech.koolping.exception.IllegalKoolPingAction;
import com.oceantech.koolping.repository.ItemRepository;
import com.oceantech.koolping.service.impl.ItemServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.neo4j.conversion.EndResult;
import org.springframework.data.neo4j.conversion.QueryResultBuilder;

import java.util.Arrays;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Sanjoy Roy
 */
public class ItemServiceImplTest {

    @InjectMocks
    private ItemService itemService;
    @Mock
    private ItemRepository mockItemRepository;

    @Before
    public void setUp(){
        itemService = new ItemServiceImpl();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldFindAll(){
        when(mockItemRepository.findAll()).thenReturn(getAll());

        Page<Item> actual = itemService.findAll();

        assertThat(actual).isNotEmpty();
        assertThat(actual.getContent().size()).isEqualTo(2);
        verify(mockItemRepository).findAll();
    }

    @Test
    public void shouldFindOne(){
        when(mockItemRepository.findOne(100L)).thenReturn(getOne(100L));

        Item actual = itemService.findById(100L);

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(100);
        verify(mockItemRepository).findOne(100L);
    }

    @Test(expected = DataRetrievalFailureException.class)
    public void shouldThrowDataRetrievalFailureExceptionWhenNotFound(){
        when(mockItemRepository.findOne(100L)).thenReturn(null);
        itemService.findById(100L);
    }

    @Test
    public void shouldFindByPlaceId(){
        Item item = getOne(100L);
        item.setPlaceId("ABC");
        when(mockItemRepository.findByPlaceId("ABC")).thenReturn(item);

        Item actual = itemService.findByPlaceId("ABC");

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(100);
        assertThat(actual.getPlaceId()).isEqualTo("ABC");
        verify(mockItemRepository).findByPlaceId("ABC");
    }

    @Test
    public void shouldCreate(){
        Item item = getOne(100L);
        item.setPlaceId("placeid");
        when(mockItemRepository.findByPlaceId(item.getPlaceId())).thenReturn(null);
        when(mockItemRepository.save(item)).thenReturn(item);

        Item actual = itemService.create(item);

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(100);
        verify(mockItemRepository).findByPlaceId(item.getPlaceId());
        verify(mockItemRepository).save(item);
    }

    @Test(expected = IllegalKoolPingAction.class)
    public void shouldThrowIllegalKoolPingActionWhenItemIsCreatedOrUpdatedWithAnExitedPlaceId(){
        Item item = getOne(100L);
        item.setPlaceId("placeid");
        when(mockItemRepository.findByPlaceId(item.getPlaceId())).thenReturn(item);

        itemService.create(item);
    }

    @Test
    public void shouldUpdate(){
        Item item = getOne(100L);
        item.setPlaceId("abc");
        Item updatedItem = getOne(100L);
        updatedItem.setPlaceId("abc");
        when(mockItemRepository.findByPlaceId(item.getPlaceId())).thenReturn(null);
        when(mockItemRepository.save(item)).thenReturn(updatedItem);

        Item actual = itemService.update(item);

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(100);
        assertThat(actual.getPlaceId()).isEqualTo("abc");
        verify(mockItemRepository).findByPlaceId(item.getPlaceId());
        verify(mockItemRepository).save(item);
    }

    @Test
    public void shouldDelete(){
        Item item = getOne(100L);
        doNothing().when(mockItemRepository).delete(item);

        itemService.delete(item);

        verify(mockItemRepository).delete(item);
    }

    // Helpers
    private EndResult<Item> getAll(){
        return new QueryResultBuilder<>(Arrays.asList(getOne(100L), getOne(200L)));
    }

    private Item getOne(Long id){
        Item item = new Item();
        item.setId(id);
        return item;
    }
}
