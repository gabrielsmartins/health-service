package br.gabrielsmartins.healthservice.application.service.analyzers;

import br.gabrielsmartins.healthservice.application.domain.Measurement;
import br.gabrielsmartins.healthservice.application.domain.enums.MeasurementClassification;
import br.gabrielsmartins.healthservice.application.domain.enums.MeasurementType;
import br.gabrielsmartins.healthservice.application.ports.in.SaveMeasurementUseCase;
import br.gabrielsmartins.healthservice.application.service.analizers.OxygenSaturationMeasurementAnalyzer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static br.gabrielsmartins.healthservice.application.support.MeasurementSupport.defaultMeasurement;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OxygenSaturationMeasurementAnalyzerTest {

    private OxygenSaturationMeasurementAnalyzer analyzer;

    @BeforeEach
    public void setup(){
        SaveMeasurementUseCase saveMeasurementUseCase = mock(SaveMeasurementUseCase.class);
        when(saveMeasurementUseCase.save(any(Measurement.class))).thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));
        this.analyzer = new OxygenSaturationMeasurementAnalyzer(saveMeasurementUseCase);
    }

    @Test
    @DisplayName("Given Oxygen Saturation When Is High Then High Classification")
    public void givenOxygenSaturationWhenIsHighThenReturnHighClassification(){
        var measurement = defaultMeasurement().build();
        measurement.setType(MeasurementType.OXYGEN_SATURATION);
        measurement.setValue(105.00);
        this.analyzer.analyze(measurement)
                .as(StepVerifier::create)
                .assertNext(m -> assertThat(m.getClassification()).isEqualTo(MeasurementClassification.HIGH))
                .verifyComplete();
    }

    @Test
    @DisplayName("Given Oxygen Saturation When Is Low Then Low Classification")
    public void givenOxygenSaturationWhenIsLowThenReturnHighClassification(){
        var measurement = defaultMeasurement().build();
        measurement.setType(MeasurementType.OXYGEN_SATURATION);
        measurement.setValue(92.00);
        this.analyzer.analyze(measurement)
                .as(StepVerifier::create)
                .assertNext(m -> assertThat(m.getClassification()).isEqualTo(MeasurementClassification.LOW))
                .verifyComplete();
    }

    @Test
    @DisplayName("Given Oxygen Saturation When Is Normal Then Normal Classification")
    public void givenOxygenSaturationWhenIsNormalThenReturnNormalClassification(){
        var measurement = defaultMeasurement().build();
        measurement.setType(MeasurementType.OXYGEN_SATURATION);
        measurement.setValue(98.00);
        this.analyzer.analyze(measurement)
                .as(StepVerifier::create)
                .assertNext(m -> assertThat(m.getClassification()).isEqualTo(MeasurementClassification.NORMAL))
                .verifyComplete();
    }
}
