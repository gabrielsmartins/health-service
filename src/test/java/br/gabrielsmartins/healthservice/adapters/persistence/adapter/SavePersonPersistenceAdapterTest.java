package br.gabrielsmartins.healthservice.adapters.persistence.adapter;

import br.gabrielsmartins.healthservice.adapters.persistence.entity.PersonEntity;
import br.gabrielsmartins.healthservice.adapters.persistence.mapper.PersonPersistenceMapper;
import br.gabrielsmartins.healthservice.adapters.persistence.service.ISavePersonPersistenceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static br.gabrielsmartins.healthservice.application.support.PersonSupport.defaultPerson;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SavePersonPersistenceAdapterTest {

    private SavePersonPersistenceAdapter adapter;
    private ISavePersonPersistenceService service;

    @BeforeEach
    public void setup(){
        this.service = mock(ISavePersonPersistenceService.class);
        var mapper = new PersonPersistenceMapper();
        this.adapter = new SavePersonPersistenceAdapter(service, mapper);
    }

    @Test
    @DisplayName("Given Person When Save Then Return Saved Person")
    public void givenPersonWhenSaveThenReturnSavedPerson(){

        when(service.save(any(PersonEntity.class))).thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        var person = defaultPerson().build();
        this.adapter.save(person)
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();
    }
}
