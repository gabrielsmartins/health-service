package br.gabrielsmartins.healthservice.adapters.persistence.service;

import br.gabrielsmartins.healthservice.adapters.persistence.entity.MeasurementEntity;
import reactor.core.publisher.Mono;

public interface ISaveMeasurementService {

    Mono<MeasurementEntity> save(MeasurementEntity measurementEntity);

}
