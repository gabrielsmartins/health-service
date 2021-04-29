package br.gabrielsmartins.healthservice.application.service.analizers;

import br.gabrielsmartins.healthservice.application.domain.Measurement;
import br.gabrielsmartins.healthservice.application.domain.enums.MeasurementClassification;
import br.gabrielsmartins.healthservice.application.domain.enums.MeasurementType;
import br.gabrielsmartins.healthservice.application.ports.in.SaveMeasurementUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public abstract class MeasurementAnalyzer {

    private final SaveMeasurementUseCase saveMeasurementUseCase;

    public Mono<Measurement> analyze(Measurement measurement){
        if(isHighLevel(measurement)){
            measurement.setClassification(MeasurementClassification.HIGH);
        }else if(isLowLevel(measurement)){
            measurement.setClassification(MeasurementClassification.LOW);
        }else {
            measurement.setClassification(MeasurementClassification.NORMAL);
        }
        measurement.setAnalyzedAt(LocalDateTime.now());
        return saveMeasurementUseCase.save(measurement);
    }

    protected abstract boolean isHighLevel(Measurement measurement);

    protected abstract boolean isLowLevel(Measurement measurement);

    protected abstract MeasurementType getMeasurementType();

}
