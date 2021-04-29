package br.gabrielsmartins.healthservice.application.service.analyzers;

import br.gabrielsmartins.healthservice.application.domain.Measurement;
import br.gabrielsmartins.healthservice.application.domain.enums.MeasurementType;
import br.gabrielsmartins.healthservice.application.ports.in.SaveMeasurementUseCase;
import br.gabrielsmartins.healthservice.application.service.analizers.HeartRateMeasurementAnalyzer;
import br.gabrielsmartins.healthservice.application.service.analizers.MeasurementAnalyzerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MeasurementAnalyzerFactoryTest {

    private MeasurementAnalyzerFactory factory;

    @BeforeEach
    public void setup(){
        SaveMeasurementUseCase saveMeasurementUseCase = mock(SaveMeasurementUseCase.class);
        when(saveMeasurementUseCase.save(any(Measurement.class))).thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));
        this.factory = new MeasurementAnalyzerFactory(List.of(new HeartRateMeasurementAnalyzer(saveMeasurementUseCase)));
    }

    @Test
    @DisplayName("Given Measurement Type When Is Valid Then Return Analyzer")
    public void givenMeasurementTypeWhenIsValidThenReturnAnalyzer(){

        var analyzer = this.factory.createAnalyzer(MeasurementType.HEART_RATE);

        assertThat(analyzer).isInstanceOf(HeartRateMeasurementAnalyzer.class);
    }
}
