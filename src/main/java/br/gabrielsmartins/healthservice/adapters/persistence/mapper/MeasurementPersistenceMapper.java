package br.gabrielsmartins.healthservice.adapters.persistence.mapper;

import br.gabrielsmartins.healthservice.adapters.persistence.entity.MeasurementEntity;
import br.gabrielsmartins.healthservice.adapters.persistence.entity.enums.MeasurementClassificationData;
import br.gabrielsmartins.healthservice.adapters.persistence.entity.enums.MeasurementTypeData;
import br.gabrielsmartins.healthservice.application.domain.Measurement;
import br.gabrielsmartins.healthservice.application.domain.enums.MeasurementClassification;
import br.gabrielsmartins.healthservice.application.domain.enums.MeasurementType;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MeasurementPersistenceMapper {

    public Measurement mapToDomain(MeasurementEntity measurementEntity){
        var mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<MeasurementEntity, Measurement>() {
            @Override
            protected void configure() {
               using((Converter<Integer, MeasurementType>) context -> MeasurementTypeData.fromCode(context.getSource()).getSource())
                      .map(this.source.getType(), this.destination.getType());
                using((Converter<String, MeasurementClassification>) context -> context.getSource() == null ? null : MeasurementClassificationData.fromPrefix(context.getSource().charAt(0)).getSource())
                        .map(this.source.getClassification(), this.destination.getClassification());
            }
        });
        return mapper.map(measurementEntity, Measurement.class);
    }

    public MeasurementEntity mapToEntity(Measurement measurement){
        var mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<Measurement, MeasurementEntity>() {
            @Override
            protected void configure() {
                using((Converter<UUID, UUID>) MappingContext::getSource)
                        .map(source.getPerson().getId(), destination.getPersonId());
                using((Converter<MeasurementType, Integer>) context -> MeasurementTypeData.fromSource(context.getSource()).getCode())
                        .map(this.source.getType(), this.destination.getType());

                using((Converter<MeasurementClassification, String>) context -> MeasurementClassificationData.fromSource(context.getSource()).getPrefix().toString())
                        .map(this.source.getClassification(), this.destination.getClassification());
            }
        });
        return mapper.map(measurement, MeasurementEntity.class);
    }
}
