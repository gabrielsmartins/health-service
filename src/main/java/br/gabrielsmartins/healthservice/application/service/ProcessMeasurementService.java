package br.gabrielsmartins.healthservice.application.service;

import br.gabrielsmartins.healthservice.application.domain.Measurement;
import br.gabrielsmartins.healthservice.application.ports.in.AnalyzeMeasurementUseCase;
import br.gabrielsmartins.healthservice.application.ports.in.ProcessMeasurementUseCase;
import br.gabrielsmartins.healthservice.application.ports.in.StoreMeasurementUseCase;
import br.gabrielsmartins.healthservice.common.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class ProcessMeasurementService implements ProcessMeasurementUseCase {

    private final StoreMeasurementUseCase storeMeasurementUseCase;
    private final AnalyzeMeasurementUseCase analyzeMeasurementUseCase;

    @Override
    public Mono<Measurement> process(Measurement measurement) {
        return storeMeasurementUseCase.store(measurement)
                                      .flatMap(analyzeMeasurementUseCase::analyze);
    }

}
