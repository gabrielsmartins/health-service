package br.gabrielsmartins.healthservice.adapters.messaging.adapter.out.mapper;

import br.gabrielsmartins.healthservice.application.domain.Measurement;
import br.gabrielsmartins.healthservice.application.domain.enums.MeasurementType;
import br.gabrielsmartins.schemas.measurement_analyzed.MeasurementAnalyzed;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class MeasurementProducerMapper {

    public MeasurementAnalyzed mapToMessage(Measurement measurement){
        var mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<Measurement, MeasurementAnalyzed>() {
            @Override
            protected void configure() {
                using((Converter<MeasurementType, br.gabrielsmartins.schemas.measurement_analyzed.MeasurementType>) context -> br.gabrielsmartins.schemas.measurement_analyzed.MeasurementType.valueOf(context.getSource().name())).map(this.source.getType(), this.destination.getMeasurementType());
            }
        });
        return mapper.map(measurement, MeasurementAnalyzed.class);
    }

}
