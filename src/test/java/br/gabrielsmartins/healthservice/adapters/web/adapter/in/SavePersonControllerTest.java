package br.gabrielsmartins.healthservice.adapters.web.adapter.in;

import br.gabrielsmartins.healthservice.adapters.web.adapter.in.mapper.PersonWebMapper;
import br.gabrielsmartins.healthservice.application.domain.Person;
import br.gabrielsmartins.healthservice.application.ports.in.SavePersonUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import static br.gabrielsmartins.healthservice.adapters.web.support.PersonDTOSupport.defaultPersonDTO;
import static br.gabrielsmartins.healthservice.application.support.PersonSupport.defaultPerson;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = SavePersonController.class)
@Import(PersonWebMapper.class)
public class SavePersonControllerTest {

    @MockBean
    private SavePersonUseCase useCase;

    @Autowired
    private WebTestClient webClient;

    private ObjectMapper mapper;

    @BeforeEach
    public void setup(){
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
    }

    @Test
    @DisplayName("Given Person When Save Then Return Saved Person")
    public void givenPersonWhenSaveThenReturnSavedPerson() throws JsonProcessingException {

        var personDTO = defaultPersonDTO().build();

        String body = mapper.writeValueAsString(personDTO);

        var person = defaultPerson().build();
        when(useCase.save(any(Person.class))).thenAnswer(invocation -> Mono.just(person));

        webClient.post()
                .uri("/health-v1/persons")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(body))
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("id").isNotEmpty();

        verify(this.useCase, times(1)).save(any(Person.class));
    }

}
