package br.gabrielsmartins.healthservice.adapters.persistence.service;

import br.gabrielsmartins.healthservice.adapters.persistence.entity.MeasurementEntity;
import br.gabrielsmartins.healthservice.adapters.persistence.repository.MeasurementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SaveMeasurementService implements ISaveMeasurementService{

    private final MeasurementRepository repository;

    @Override
    public Mono<MeasurementEntity> save(MeasurementEntity measurementEntity) {
        return this.repository.save(measurementEntity);
    }
}
