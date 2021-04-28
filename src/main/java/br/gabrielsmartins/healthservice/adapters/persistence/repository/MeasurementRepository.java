package br.gabrielsmartins.healthservice.adapters.persistence.repository;

import br.gabrielsmartins.healthservice.adapters.persistence.entity.MeasurementEntity;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends ReactiveSortingRepository<MeasurementEntity, Long> {

}
