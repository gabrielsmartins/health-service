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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = SavePersonController.class)
@Import(PersonWebMapper.class)
public class ExceptionHandlerControllerTest {

    @MockBean
    private SavePersonUseCase useCase;

    @Autowired
    private WebTestClient webClient;

    private ObjectMapper mapper;

    @BeforeEach
    public void setup() {
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
    }

    @Test
    @DisplayName("Given Person When Is Invalid Then Return Bad Request")
    public void givenPersonWhenIsInvalidThenReturnBadRequest() throws JsonProcessingException {

        var personDTO = defaultPersonDTO().build();

        personDTO.setFirstName("");


        String body = mapper.writeValueAsString(personDTO);

        when(useCase.save(any(Person.class))).thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        webClient.post()
                .uri("/health-v1/persons")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(body))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .jsonPath("fields").isNotEmpty();
    }
}