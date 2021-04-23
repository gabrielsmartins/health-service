package br.gabrielsmartins.healthservice.adapters.persistence.adapter;

import br.gabrielsmartins.healthservice.adapters.persistence.mapper.MeasurementPersistenceMapper;
import br.gabrielsmartins.healthservice.adapters.persistence.service.ISaveMeasurementService;
import br.gabrielsmartins.healthservice.application.domain.Measurement;
import br.gabrielsmartins.healthservice.application.ports.out.SaveMeasurementPort;
import br.gabrielsmartins.healthservice.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@PersistenceAdapter
@RequiredArgsConstructor
public class SaveMeasurementPersistenceMapper implements SaveMeasurementPort {

    private final ISaveMeasurementService service;
    private final MeasurementPersistenceMapper mapper;

    @Override
    public Mono<Measurement> save(Measurement measurement) {
        var measurementEntity = this.mapper.mapToEntity(measurement);
        return this.service.save(measurementEntity).map(this.mapper::mapToDomain);
    }
}
