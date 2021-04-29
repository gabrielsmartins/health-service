package br.gabrielsmartins.healthservice.application.service;

import br.gabrielsmartins.healthservice.application.domain.Measurement;
import br.gabrielsmartins.healthservice.application.domain.enums.MeasurementClassification;
import br.gabrielsmartins.healthservice.application.domain.enums.MeasurementType;
import br.gabrielsmartins.healthservice.application.ports.in.NotifyMeasurementUseCase;
import br.gabrielsmartins.healthservice.application.service.analizers.MeasurementAnalyzer;
import br.gabrielsmartins.healthservice.application.service.analizers.MeasurementAnalyzerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static br.gabrielsmartins.healthservice.application.support.MeasurementSupport.defaultMeasurement;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AnalyzeMeasurementServiceTest {

    private AnalyzeMeasurementService service;
    private NotifyMeasurementUseCase useCase;
    private MeasurementAnalyzerFactory factory;

    @BeforeEach
    public void setup(){
        this.useCase = mock(NotifyMeasurementUseCase.class);
        this.factory = mock(MeasurementAnalyzerFactory.class);
        this.service = new AnalyzeMeasurementService(useCase, factory);
    }

    @Test
    @DisplayName("Given Measurement When Is Normal Then Return Measurement")
    public void givenMeasurementWhenIsNormalThenReturnMeasurement(){
        var measurement = defaultMeasurement().build();
        measurement.setClassification(MeasurementClassification.NORMAL);

        var analyzer = mock(MeasurementAnalyzer.class);
        when(analyzer.analyze(any(Measurement.class))).thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        when(factory.createAnalyzer(any(MeasurementType.class))).thenReturn(analyzer);

        this.service.analyze(measurement)
                    .as(StepVerifier::create)
                    .expectNextCount(1)
                    .verifyComplete();

        verify(useCase, never()).notify(any(Measurement.class));
    }

    @Test
    @DisplayName("Given Measurement When Is Not Normal Then Send Notification")
    public void givenMeasurementWhenIsNotNormalThenSendNotification(){
        var measurement = defaultMeasurement().build();
        measurement.setClassification(MeasurementClassification.HIGH);

        var analyzer = mock(MeasurementAnalyzer.class);
        when(analyzer.analyze(any(Measurement.class))).thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        when(factory.createAnalyzer(any(MeasurementType.class))).thenReturn(analyzer);
        when(useCase.notify(any(Measurement.class))).thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        this.service.analyze(measurement)
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();

        verify(useCase, times(1)).notify(any(Measurement.class));
    }

}
