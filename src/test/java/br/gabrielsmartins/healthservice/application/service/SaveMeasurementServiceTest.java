package br.gabrielsmartins.healthservice.application.service;

import br.gabrielsmartins.healthservice.application.domain.Measurement;
import br.gabrielsmartins.healthservice.application.ports.out.SaveMeasurementPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static br.gabrielsmartins.healthservice.application.support.MeasurementSupport.defaultMeasurement;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SaveMeasurementServiceTest {

    private SaveMeasurementService service;
    private SaveMeasurementPort port;

    @BeforeEach
    public void setup(){
        this.port = mock(SaveMeasurementPort.class);
        this.service = new SaveMeasurementService(port);
    }

    @Test
    @DisplayName("Given Measurement When Save Then Return Saved Measurement")
    public void givenPMeasurementWhenSaveThenReturnSavedMeasurement(){

        when(port.save(any(Measurement.class))).thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        var measurement = defaultMeasurement().build();
        this.service.save(measurement)
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();
    }
}
