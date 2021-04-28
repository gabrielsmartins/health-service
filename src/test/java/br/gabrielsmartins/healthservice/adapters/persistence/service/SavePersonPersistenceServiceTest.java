package br.gabrielsmartins.healthservice.adapters.persistence.service;

import br.gabrielsmartins.healthservice.adapters.persistence.entity.PersonEntity;
import br.gabrielsmartins.healthservice.adapters.persistence.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static br.gabrielsmartins.healthservice.adapters.persistence.support.PersonEntitySupport.defaultPersonEntity;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SavePersonPersistenceServiceTest {

    private SavePersonPersistenceService service;
    private PersonRepository repository;

    @BeforeEach
    public void setup(){
        this.repository = mock(PersonRepository.class);
        this.service = new SavePersonPersistenceService(repository);
    }

    @Test
    @DisplayName("Given Person When Save Then Return Saved Person")
    public void givenPersonWhenSaveThenReturnSavedPerson(){

        when(repository.save(any(PersonEntity.class))).thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        var personEntity = defaultPersonEntity().build();
        this.service.save(personEntity)
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();
    }
}
