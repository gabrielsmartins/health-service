package br.gabrielsmartins.healthservice.application.service;

import br.gabrielsmartins.healthservice.application.domain.Measurement;
import br.gabrielsmartins.healthservice.application.ports.in.AnalyzeMeasurementUseCase;
import br.gabrielsmartins.healthservice.application.ports.in.StoreMeasurementUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static br.gabrielsmartins.healthservice.application.support.MeasurementSupport.defaultMeasurement;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProcessMeasurementServiceTest {

    private ProcessMeasurementService service;
    private StoreMeasurementUseCase storeMeasurementUseCase;
    private AnalyzeMeasurementUseCase analyzeMeasurementUseCase;

    @BeforeEach
    public void setup(){
        this.storeMeasurementUseCase = mock(StoreMeasurementUseCase.class);
        this.analyzeMeasurementUseCase = mock(AnalyzeMeasurementUseCase.class);
        this.service = new ProcessMeasurementService(storeMeasurementUseCase, analyzeMeasurementUseCase);
    }

    @Test
    @DisplayName("Given Measurement When Process Then Return Processed Measurement")
    public void givenMeasurementWhenProcessThenReturnProcessedMeasurement(){
        var measurement = defaultMeasurement().build();

        when(storeMeasurementUseCase.store(any(Measurement.class))).thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));
        when(analyzeMeasurementUseCase.analyze(any(Measurement.class))).thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        this.service.process(measurement)
                    .as(StepVerifier::create)
                    .expectNextCount(1)
                    .verifyComplete();
    }
}
