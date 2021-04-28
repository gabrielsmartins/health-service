package br.gabrielsmartins.healthservice.application.service;

import br.gabrielsmartins.healthservice.application.domain.Measurement;
import br.gabrielsmartins.healthservice.application.ports.in.SaveMeasurementUseCase;
import br.gabrielsmartins.healthservice.application.ports.in.SearchPersonUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static br.gabrielsmartins.healthservice.application.support.MeasurementSupport.defaultMeasurement;
import static br.gabrielsmartins.healthservice.application.support.PersonSupport.defaultPerson;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StoreMeasurementServiceTest {

    private StoreMeasurementService service;
    private SearchPersonUseCase searchPersonUseCase;
    private SaveMeasurementUseCase saveMeasurementUseCase;

    @BeforeEach
    public void setup(){
        this.searchPersonUseCase = mock(SearchPersonUseCase.class);
        this.saveMeasurementUseCase = mock(SaveMeasurementUseCase.class);
        this.service = new StoreMeasurementService(searchPersonUseCase, saveMeasurementUseCase);
    }

    @Test
    @DisplayName("Given Measurement When Person Not Exists Then Throw Exception")
    public void givenMeasurementWhenPersonNotExistsThenThrowException(){
        var person = defaultPerson().build();

        var measurement = defaultMeasurement()
                .withPerson(person)
                .build();

        when(searchPersonUseCase.findById(any(UUID.class))).thenReturn(Mono.empty());

        this.service.store(measurement)
                    .as(StepVerifier::create)
                    .expectError(IllegalArgumentException.class)
                    .verify();
    }

    @Test
    @DisplayName("Given Measurement When Person Exists Then Store Measurement")
    public void givenMeasurementWhenPersonNotExistsThenStoreMeasurement(){


        var person = defaultPerson().build();

        var measurement = defaultMeasurement()
                                            .withPerson(person)
                                            .build();

        when(searchPersonUseCase.findById(any(UUID.class))).thenReturn(Mono.just(person));

        when(saveMeasurementUseCase.save(any(Measurement.class))).thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        this.service.store(measurement)
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();
    }
}
