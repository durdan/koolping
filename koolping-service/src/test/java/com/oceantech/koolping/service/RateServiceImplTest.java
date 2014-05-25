package com.oceantech.koolping.service;

import com.oceantech.koolping.domain.Item;
import com.oceantech.koolping.domain.Person;
import com.oceantech.koolping.domain.Rate;
import com.oceantech.koolping.repository.RateRepository;
import com.oceantech.koolping.service.impl.RateServiceImpl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataRetrievalFailureException;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Sanjoy Roy
 */
public class RateServiceImplTest {

    @InjectMocks
    private RateService rateService;
    @Mock
    private RateRepository mockRateRepository;

    @Before
    public void setUp(){
        rateService = new RateServiceImpl();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldFindOne(){
        when(mockRateRepository.findOne(100L)).thenReturn(getOne(100L));

        Rate actual = rateService.findById(100L);

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(100);
        verify(mockRateRepository).findOne(100L);
    }

    @Test(expected = DataRetrievalFailureException.class)
    public void shouldThrowDataRetrievalFailureExceptionWhenNotFound(){
        when(mockRateRepository.findOne(100L)).thenReturn(null);
        rateService.findById(100L);
    }

    @Test
    public void shouldCreate(){
        Rate rate = getOne(100L);
        when(mockRateRepository.save(rate)).thenReturn(rate);

        Rate actual = rateService.createOrUpdate(rate);

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(100);
        verify(mockRateRepository).save(rate);
    }

    @Test
    public void shouldDelete(){
        Rate rate = getOne(100L);
        doNothing().when(mockRateRepository).delete(rate);

        rateService.delete(rate);

        verify(mockRateRepository).delete(rate);
    }

    // Helper
    private Rate getOne(Long id){
        Rate rate = new Rate();
        rate.setId(id);
        rate.setItem(new Item());
        rate.setPerson(new Person());
        rate.setStar(2);
        return rate;
    }
}
