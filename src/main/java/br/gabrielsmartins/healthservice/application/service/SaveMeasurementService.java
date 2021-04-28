package br.gabrielsmartins.healthservice.application.service;

import br.gabrielsmartins.healthservice.application.domain.Measurement;
import br.gabrielsmartins.healthservice.application.ports.in.SaveMeasurementUseCase;
import br.gabrielsmartins.healthservice.application.ports.out.SaveMeasurementPort;
import br.gabrielsmartins.healthservice.common.UseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@UseCase
@RequiredArgsConstructor
public class SaveMeasurementService implements SaveMeasurementUseCase {

    private final SaveMeasurementPort port;

    @Override
    public Mono<Measurement> save(Measurement measurement) {
        return this.port.save(measurement);
    }
}
