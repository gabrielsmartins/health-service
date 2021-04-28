package br.gabrielsmartins.healthservice.adapters.persistence.adapter;

import br.gabrielsmartins.healthservice.adapters.persistence.entity.MeasurementEntity;
import br.gabrielsmartins.healthservice.adapters.persistence.mapper.MeasurementPersistenceMapper;
import br.gabrielsmartins.healthservice.adapters.persistence.service.ISaveMeasurementPersistenceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static br.gabrielsmartins.healthservice.application.support.MeasurementSupport.defaultMeasurement;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SaveMeasurementPersistenceAdapterTest {

    private SaveMeasurementPersistenceAdapter adapter;
    private ISaveMeasurementPersistenceService service;

    @BeforeEach
    public void setup(){
        this.service = mock(ISaveMeasurementPersistenceService.class);
        var mapper = new MeasurementPersistenceMapper();
        this.adapter = new SaveMeasurementPersistenceAdapter(service, mapper);
    }

    @Test
    @DisplayName("Given Measurement When Save Then Return Saved Measurement")
    public void givenPMeasurementWhenSaveThenReturnSavedMeasurement(){

        when(service.save(any(MeasurementEntity.class))).thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        var measurement = defaultMeasurement().build();
        this.adapter.save(measurement)
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();
    }
}
