package br.gabrielsmartins.healthservice.adapters.persistence.service;

import br.gabrielsmartins.healthservice.adapters.persistence.entity.MeasurementEntity;
import br.gabrielsmartins.healthservice.adapters.persistence.repository.MeasurementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static br.gabrielsmartins.healthservice.adapters.persistence.support.MeasurementEntitySupport.defaultMeasurementEntity;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SaveMeasurementPersistenceServiceTest {

    private SaveMeasurementPersistenceService service;
    private MeasurementRepository repository;

    @BeforeEach
    public void setup(){
        this.repository = mock(MeasurementRepository.class);
        this.service = new SaveMeasurementPersistenceService(repository);
    }

    @Test
    @DisplayName("Given Measurement When Save Then Return Saved Measurement")
    public void givenPMeasurementWhenSaveThenReturnSavedMeasurement(){

        when(repository.save(any(MeasurementEntity.class))).thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        var measurementEntity = defaultMeasurementEntity().build();
        this.service.save(measurementEntity)
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();
    }
}
