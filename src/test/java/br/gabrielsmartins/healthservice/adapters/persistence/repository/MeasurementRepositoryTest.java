package br.gabrielsmartins.healthservice.adapters.persistence.repository;

import br.gabrielsmartins.healthservice.adapters.persistence.config.DatabaseConfiguration;
import br.gabrielsmartins.healthservice.adapters.persistence.entity.PersonEntity;
import br.gabrielsmartins.healthservice.adapters.persistence.support.DatabaseConfigSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import reactor.test.StepVerifier;

import static br.gabrielsmartins.healthservice.adapters.persistence.support.MeasurementEntitySupport.defaultMeasurementEntity;
import static br.gabrielsmartins.healthservice.adapters.persistence.support.PersonEntitySupport.defaultPersonEntity;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataR2dbcTest
@Import({DatabaseConfiguration.class,
        DatabaseConfigSupport.class})
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class MeasurementRepositoryTest {

    @Autowired
    private MeasurementRepository repository;

    @Autowired
    private PersonRepository personRepository;

    private PersonEntity personEntity;


    @BeforeEach
    public void setup(){
        this.personEntity = defaultPersonEntity()
                .withId(null)
                .build();
        this.personRepository.save(personEntity);
    }

    @Test
    @DisplayName("Given Measurement When Save Then Return Saved Measurement")
    public void givenPMeasurementWhenSaveThenReturnSavedMeasurement(){

        var measurementEntity = defaultMeasurementEntity()
                                                        .withId(null)
                                                        .withPersonId(personEntity.getId())
                                                        .build();

        this.repository.save(measurementEntity)
                .as(StepVerifier::create)
                .assertNext(m -> assertThat(m).isNotNull())
                .verifyComplete();
    }

}
