package br.gabrielsmartins.healthservice.adapters.persistence.service;

import br.gabrielsmartins.healthservice.adapters.persistence.repository.PersonRepository;
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

public class SearchPersonPersistenceServiceTest {

    private SearchPersonPersistenceService service;
    private PersonRepository repository;

    @BeforeEach
    public void setup(){
        this.repository = mock(PersonRepository.class);
        this.service = new SearchPersonPersistenceService(repository);
    }

    @Test
    @DisplayName("Given Person Id When Exists Then Return Person")
    public void givenPersonIdWhenExistsThenReturnPerson(){

        var personEntity = defaultPersonEntity().build();

        when(repository.findById(any(UUID.class))).thenReturn(Mono.just(personEntity));

        this.service.findById(personEntity.getId())
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();
    }
}
