package br.gabrielsmartins.healthservice.application.ports.out;

import br.gabrielsmartins.healthservice.application.domain.Measurement;
import reactor.core.publisher.Mono;

public interface NotifyMeasurementPort {

    Mono<Measurement> notify(Measurement measurement);

}
