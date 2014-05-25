package com.oceantech.koolping.service;

import com.oceantech.koolping.domain.Category;
import com.oceantech.koolping.exception.IllegalKoolPingAction;
import com.oceantech.koolping.repository.CategoryRepository;
import com.oceantech.koolping.service.impl.CategoryServiceImpl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.neo4j.conversion.EndResult;
import org.springframework.data.neo4j.conversion.QueryResultBuilder;

import java.util.Arrays;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Sanjoy Roy
 */
public class CategoryServiceImplTest {

    @InjectMocks
    private CategoryService categoryService;
    @Mock
    private CategoryRepository mockCategoryRepository;

    @Before
    public void setUp(){
        categoryService = new CategoryServiceImpl();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldFindAll(){
        when(mockCategoryRepository.findAll()).thenReturn(getAll());

        List<Category> actual = categoryService.findAll();

        assertThat(actual).isNotEmpty();
        assertThat(actual.size()).isEqualTo(2);
        verify(mockCategoryRepository).findAll();
    }

    @Test
    public void shouldFindOne(){
        when(mockCategoryRepository.findOne(100L)).thenReturn(getOne(100L));

        Category actual = categoryService.findById(100L);

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(100);
        verify(mockCategoryRepository).findOne(100L);
    }

    @Test(expected = DataRetrievalFailureException.class)
    public void shouldThrowDataRetrievalFailureExceptionWhenNotFound(){
        when(mockCategoryRepository.findOne(100L)).thenReturn(null);
        categoryService.findById(100L);
    }

    @Test
    public void shouldFindByName(){
        Category category = getOne(100L);
        category.setName("park");
        when(mockCategoryRepository.findByName("park")).thenReturn(category);

        Category actual = categoryService.findByName("park");

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(100);
        assertThat(actual.getName()).isEqualTo("park");
        verify(mockCategoryRepository).findByName("park");
    }

    @Test
    public void shouldCreate(){
        Category category = getOne(100L);
        category.setName("cafe");
        when(mockCategoryRepository.findByName(category.getName())).thenReturn(null);
        when(mockCategoryRepository.save(category)).thenReturn(category);

        Category actual = categoryService.create(category);

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(100);
        verify(mockCategoryRepository).findByName(category.getName());
        verify(mockCategoryRepository).save(category);
    }

    @Test(expected = IllegalKoolPingAction.class)
    public void shouldThrowIllegalKoolPingActionWhenCategoryIsCreatedOrUpdatedWithAnExitedName(){
        Category category = getOne(100L);
        category.setName("cafe");
        when(mockCategoryRepository.findByName(category.getName())).thenReturn(category);

        categoryService.create(category);
    }

    @Test
    public void shouldDelete(){
        Category category = getOne(100L);
        doNothing().when(mockCategoryRepository).delete(category);

        categoryService.delete(category);

        verify(mockCategoryRepository).delete(category);
    }

    // Helpers
    private EndResult<Category> getAll(){
        return new QueryResultBuilder<>(Arrays.asList(getOne(100L), getOne(200L)));
    }

    private Category getOne(Long id){
        Category category = new Category();
        category.setId(id);
        return category;
    }
}
