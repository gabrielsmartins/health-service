package br.gabrielsmartins.healthservice.application.service;

import br.gabrielsmartins.healthservice.application.domain.Person;
import br.gabrielsmartins.healthservice.application.ports.out.SavePersonPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static br.gabrielsmartins.healthservice.application.support.PersonSupport.defaultPerson;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SavePersonServiceTest {

    private SavePersonService service;
    private SavePersonPort port;

    @BeforeEach
    public void setup(){
        this.port = mock(SavePersonPort.class);
        this.service = new SavePersonService(port);
    }

    @Test
    @DisplayName("Given Person When Save Then Return Saved Person")
    public void givenPersonWhenSaveThenReturnSavedPerson(){

        when(port.save(any(Person.class))).thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        var person = defaultPerson().build();
        this.service.save(person)
                    .as(StepVerifier::create)
                    .expectNextCount(1)
                    .verifyComplete();
    }
}
