package br.gabrielsmartins.healthservice.adapters.persistence.adapter;

import br.gabrielsmartins.healthservice.adapters.persistence.mapper.MeasurementPersistenceMapper;
import br.gabrielsmartins.healthservice.adapters.persistence.service.ISaveMeasurementPersistenceService;
import br.gabrielsmartins.healthservice.application.domain.Measurement;
import br.gabrielsmartins.healthservice.application.ports.out.SaveMeasurementPort;
import br.gabrielsmartins.healthservice.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@PersistenceAdapter
@RequiredArgsConstructor
public class SaveMeasurementPersistenceAdapter implements SaveMeasurementPort {

    private final ISaveMeasurementPersistenceService service;
    private final MeasurementPersistenceMapper mapper;

    @Override
    public Mono<Measurement> save(Measurement measurement) {
        var measurementEntity = this.mapper.mapToEntity(measurement);
        return this.service.save(measurementEntity)
                           .map(m -> {
                               var savedMeasurement = this.mapper.mapToDomain(m);
                               savedMeasurement.setPerson(measurement.getPerson());
                               return savedMeasurement;
                           });
    }
}
