package br.gabrielsmartins.healthservice.application.ports.out;

import br.gabrielsmartins.healthservice.application.domain.Measurement;
import reactor.core.publisher.Mono;

public interface SaveMeasurementPort {

    Mono<Measurement> save(Measurement measurement);

}
