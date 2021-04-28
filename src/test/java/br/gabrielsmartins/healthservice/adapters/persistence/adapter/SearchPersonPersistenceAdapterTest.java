package br.gabrielsmartins.healthservice.adapters.persistence.adapter;

import br.gabrielsmartins.healthservice.adapters.persistence.mapper.PersonPersistenceMapper;
import br.gabrielsmartins.healthservice.adapters.persistence.service.ISearchPersonPersistenceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static br.gabrielsmartins.healthservice.adapters.persistence.support.PersonEntitySupport.defaultPersonEntity;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SearchPersonPersistenceAdapterTest {

    private SearchPersonPersistenceAdapter adapter;
    private ISearchPersonPersistenceService service;

    @BeforeEach
    public void setup(){
        this.service = mock(ISearchPersonPersistenceService.class);
        var mapper = new PersonPersistenceMapper();
        this.adapter = new SearchPersonPersistenceAdapter(service, mapper);
    }

    @Test
    @DisplayName("Given Person Id When Exists Then Return Person")
    public void givenPersonIdWhenExistsThenReturnPerson(){

        var personEntity = defaultPersonEntity().build();

        when(service.findById(any(UUID.class))).thenReturn(Mono.just(personEntity));

        this.adapter.findById(personEntity.getId())
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();
    }
}
