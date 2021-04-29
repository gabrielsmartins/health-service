package br.gabrielsmartins.healthservice.application.service;

import br.gabrielsmartins.healthservice.application.domain.Measurement;
import br.gabrielsmartins.healthservice.application.ports.out.NotifyMeasurementPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static br.gabrielsmartins.healthservice.application.support.MeasurementSupport.defaultMeasurement;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NotifyMeasurementServiceTest {

    private NotifyMeasurementService service;
    private NotifyMeasurementPort port;

    @BeforeEach
    public void setup(){
        this.port = mock(NotifyMeasurementPort.class);
        this.service = new NotifyMeasurementService(port);
    }

    @Test
    @DisplayName("Given Measurement When Notify Then Return Notified Measurement")
    public void givenMeasurementWhenNotifyThenReturnNotifiedMeasurement(){

        var measurement = defaultMeasurement().build();

        when(this.port.notify(any(Measurement.class))).thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        this.service.notify(measurement)
                    .as(StepVerifier::create)
                    .expectNextCount(1)
                    .verifyComplete();
    }


}
