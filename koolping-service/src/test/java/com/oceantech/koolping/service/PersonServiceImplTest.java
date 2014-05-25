package com.oceantech.koolping.service;

import com.oceantech.koolping.domain.Person;
import com.oceantech.koolping.exception.IllegalKoolPingAction;
import com.oceantech.koolping.repository.PersonRepository;
import com.oceantech.koolping.service.impl.PersonServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.neo4j.conversion.QueryResultBuilder;
import org.springframework.data.neo4j.conversion.EndResult;

import java.util.Arrays;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Sanjoy Roy
 */
public class PersonServiceImplTest {

    @InjectMocks
    private PersonService personService;
    @Mock
    private PersonRepository mockPersonRepository;

    @Before
    public void setUp(){
        personService = new PersonServiceImpl();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldFindAll(){
        when(mockPersonRepository.findAll()).thenReturn(getAll());

        List<Person> actual = personService.findAll();

        assertThat(actual).isNotEmpty();
        assertThat(actual.size()).isEqualTo(2);
        verify(mockPersonRepository).findAll();
    }

    @Test
    public void shouldFindOne(){
        when(mockPersonRepository.findOne(100L)).thenReturn(getOne(100L));

        Person actual = personService.findById(100L);

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(100);
        verify(mockPersonRepository).findOne(100L);
    }

    @Test(expected = DataRetrievalFailureException.class)
    public void shouldThrowDataRetrievalFailureExceptionWhenNotFound(){
        when(mockPersonRepository.findOne(100L)).thenReturn(null);
        personService.findById(100L);
    }

    @Test
    public void shouldFindByUsername(){
        Person person = getOne(100L);
        person.setUsername("username");
        when(mockPersonRepository.findByUsername("username")).thenReturn(person);

        Person actual = personService.findByUsername("username");

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(100);
        assertThat(actual.getUsername()).isEqualTo("username");
        verify(mockPersonRepository).findByUsername("username");
    }

    @Test
    public void shouldFindByFacebookId(){
        Person person = getOne(100L);
        person.setFacebookId("asasdsdsdada");
        when(mockPersonRepository.findByFacebookId("asasdsdsdada")).thenReturn(person);

        Person actual = personService.findByFacebookId("asasdsdsdada");

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(100);
        assertThat(actual.getFacebookId()).isEqualTo("asasdsdsdada");
        verify(mockPersonRepository).findByFacebookId("asasdsdsdada");
    }

    @Test
    public void shouldFindByTwitterId(){
        Person person = getOne(100L);
        person.setTwitterId("asasdsdsdada");
        when(mockPersonRepository.findByTwitterId("asasdsdsdada")).thenReturn(person);

        Person actual = personService.findByTwitterId("asasdsdsdada");

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(100);
        assertThat(actual.getTwitterId()).isEqualTo("asasdsdsdada");
        verify(mockPersonRepository).findByTwitterId("asasdsdsdada");
    }

    @Test
    public void shouldFindByGoogleplusId(){
        Person person = getOne(100L);
        person.setGoogleplusId("asasdsdsdada");
        when(mockPersonRepository.findByGoogleplusId("asasdsdsdada")).thenReturn(person);

        Person actual = personService.findByGoogleplusId("asasdsdsdada");

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(100);
        assertThat(actual.getGoogleplusId()).isEqualTo("asasdsdsdada");
        verify(mockPersonRepository).findByGoogleplusId("asasdsdsdada");
    }

    @Test
    public void shouldCreate(){
        Person person = getOne(100L);
        person.setUsername("username");
        when(mockPersonRepository.findByUsername(person.getUsername())).thenReturn(null);
        when(mockPersonRepository.save(person)).thenReturn(person);

        Person actual = personService.create(person);

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(100);
        verify(mockPersonRepository).findByUsername(person.getUsername());
        verify(mockPersonRepository).save(person);
    }

    @Test(expected = IllegalKoolPingAction.class)
    public void shouldThrowIllegalKoolPingActionWhenPersonIsCreatedWithAnExitedUsername(){
        Person person = getOne(100L);
        person.setUsername("username");
        when(mockPersonRepository.findByUsername(person.getUsername())).thenReturn(person);

        personService.create(person);
    }

    @Test
    public void shouldUpdate(){
        Person person = getOne(100L);
        person.setUsername("username");
        Person updatedPerson = getOne(100L);
        updatedPerson.setFirstName("firstName");
        updatedPerson.setUsername("username");
        when(mockPersonRepository.save(person)).thenReturn(updatedPerson);

        Person actual = personService.update(person);

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(100);
        assertThat(actual.getUsername()).isEqualTo("username");
        assertThat(actual.getFirstName()).isEqualTo("firstName");
        verify(mockPersonRepository).save(person);
    }

    @Test
    public void shouldDelete(){
        Person person = getOne(100L);
        doNothing().when(mockPersonRepository).delete(person);

        personService.delete(person);

        verify(mockPersonRepository).delete(person);
    }

    // Helpers
    private EndResult<Person> getAll(){
        return new QueryResultBuilder<>(Arrays.asList(getOne(100L), getOne(200L)));
    }

    private Person getOne(Long id){
        Person person = new Person();
        person.setId(id);
        return person;
    }
}
