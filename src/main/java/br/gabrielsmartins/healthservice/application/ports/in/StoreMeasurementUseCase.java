package br.gabrielsmartins.healthservice.application.ports.in;

import br.gabrielsmartins.healthservice.application.domain.Measurement;
import reactor.core.publisher.Mono;

public interface StoreMeasurementUseCase {

    Mono<Measurement> store(Measurement measurement);

}
