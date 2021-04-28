package br.gabrielsmartins.healthservice.application.service;

import br.gabrielsmartins.healthservice.application.domain.Measurement;
import br.gabrielsmartins.healthservice.application.ports.in.SaveMeasurementUseCase;
import br.gabrielsmartins.healthservice.application.ports.in.SearchPersonUseCase;
import br.gabrielsmartins.healthservice.application.ports.in.StoreMeasurementUseCase;
import br.gabrielsmartins.healthservice.common.UseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
public class StoreMeasurementService implements StoreMeasurementUseCase {

    private final SearchPersonUseCase searchPersonUseCase;
    private final SaveMeasurementUseCase saveMeasurementUseCase;

    @Override
    public Mono<Measurement> store(Measurement measurement) {
        UUID personId = measurement.getPerson().getId();
        return this.searchPersonUseCase.findById(personId)
                                .switchIfEmpty(Mono.error(new IllegalArgumentException("Person not found for ID: " + personId)))
                                .flatMap(person -> {
                                    measurement.setPerson(person);
                                    return this.saveMeasurementUseCase.save(measurement);
                                });
    }
}
