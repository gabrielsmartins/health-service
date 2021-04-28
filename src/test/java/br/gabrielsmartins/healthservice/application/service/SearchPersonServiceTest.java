package br.gabrielsmartins.healthservice.application.service;

import br.gabrielsmartins.healthservice.application.ports.out.SearchPersonPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static br.gabrielsmartins.healthservice.application.support.PersonSupport.defaultPerson;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SearchPersonServiceTest {

    private SearchPersonService service;
    private SearchPersonPort port;

    @BeforeEach
    public void setup(){
        this.port = mock(SearchPersonPort.class);
        this.service = new SearchPersonService(port);
    }

    @Test
    @DisplayName("Given Person Id When Exists Then Return Person")
    public void givenPersonIdWhenExistsThenReturnPerson(){

        var person = defaultPerson().build();

        when(port.findById(any(UUID.class))).thenReturn(Mono.just(person));

        this.service.findById(person.getId())
                    .as(StepVerifier::create)
                    .expectNextCount(1)
                    .verifyComplete();
    }
}
