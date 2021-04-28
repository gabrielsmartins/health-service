package br.gabrielsmartins.healthservice.adapters.persistence.repository;

import br.gabrielsmartins.healthservice.adapters.persistence.config.DatabaseConfiguration;
import br.gabrielsmartins.healthservice.adapters.persistence.support.DatabaseConfigSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import reactor.test.StepVerifier;

import static br.gabrielsmartins.healthservice.adapters.persistence.support.PersonEntitySupport.defaultPersonEntity;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataR2dbcTest
@Import({DatabaseConfiguration.class,
        DatabaseConfigSupport.class})
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository repository;

    @Test
    @DisplayName("Given Person When Save Then Return Saved Person")
    public void givenPersonWhenSaveThenReturnSavedPerson(){
        var personEntity = defaultPersonEntity()
                                                .withId(null)
                                                .build();
        this.repository.save(personEntity)
                .as(StepVerifier::create)
                .assertNext(p -> assertThat(p).isNotNull())
                .verifyComplete();
    }

    @Test
    @DisplayName("Given Person Id When Exists Then Return Person")
    public void givenPersonIdWhenExistsThenReturnPerson(){

        var personEntity = defaultPersonEntity().build();
        this.repository.save(personEntity).block();

        this.repository.findById(personEntity.getId())
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();
    }
}
