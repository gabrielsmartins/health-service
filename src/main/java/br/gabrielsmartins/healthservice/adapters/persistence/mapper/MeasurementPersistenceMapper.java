package br.gabrielsmartins.healthservice.adapters.persistence.mapper;

import br.gabrielsmartins.healthservice.adapters.persistence.entity.MeasurementEntity;
import br.gabrielsmartins.healthservice.application.domain.Measurement;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MeasurementPersistenceMapper {

    public Measurement mapToDomain(MeasurementEntity measurementEntity){
        var mapper = new ModelMapper();
        return mapper.map(measurementEntity, Measurement.class);
    }

    public MeasurementEntity mapToEntity(Measurement measurement){
        var mapper = new ModelMapper();
        return mapper.map(measurement, MeasurementEntity.class);
    }
}
