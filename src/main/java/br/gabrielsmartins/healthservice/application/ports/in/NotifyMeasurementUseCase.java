package br.gabrielsmartins.healthservice.application.ports.in;

import br.gabrielsmartins.healthservice.application.domain.Measurement;
import reactor.core.publisher.Mono;

public interface NotifyMeasurementUseCase {

    Mono<Measurement> notify(Measurement measurement);

}
