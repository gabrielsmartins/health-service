package br.gabrielsmartins.healthservice.application.service;

import br.gabrielsmartins.healthservice.application.domain.Measurement;
import br.gabrielsmartins.healthservice.application.domain.enums.MeasurementClassification;
import br.gabrielsmartins.healthservice.application.ports.in.AnalyzeMeasurementUseCase;
import br.gabrielsmartins.healthservice.application.ports.in.NotifyMeasurementUseCase;
import br.gabrielsmartins.healthservice.application.service.analizers.MeasurementAnalyzerFactory;
import br.gabrielsmartins.healthservice.common.UseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@UseCase
@RequiredArgsConstructor
public class AnalyzeMeasurementService implements AnalyzeMeasurementUseCase {

    private final NotifyMeasurementUseCase useCase;
    private final MeasurementAnalyzerFactory factory;

    @Override
    public Mono<Measurement> analyze(Measurement measurement) {
        var analyzer = this.factory.createAnalyzer(measurement.getType());
        return analyzer.analyze(measurement)
                       .flatMap(this::evaluate);
    }

    private Mono<Measurement> evaluate(Measurement measurement) {
        if(measurement.getClassification() == MeasurementClassification.NORMAL)
            return Mono.just(measurement);
        return useCase.notify(measurement);
    }

}
