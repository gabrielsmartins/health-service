package br.gabrielsmartins.healthservice.application.service;

import br.gabrielsmartins.healthservice.application.domain.Measurement;
import br.gabrielsmartins.healthservice.application.ports.in.NotifyMeasurementUseCase;
import br.gabrielsmartins.healthservice.application.ports.out.NotifyMeasurementPort;
import br.gabrielsmartins.healthservice.common.UseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@UseCase
@RequiredArgsConstructor
public class NotifyMeasurementService implements NotifyMeasurementUseCase {

    private final NotifyMeasurementPort port;

    @Override
    public Mono<Measurement> notify(Measurement measurement) {
        return this.port.notify(measurement);
    }

}
