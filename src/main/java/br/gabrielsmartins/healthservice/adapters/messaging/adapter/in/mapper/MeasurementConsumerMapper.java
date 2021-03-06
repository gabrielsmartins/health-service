package br.gabrielsmartins.healthservice.adapters.messaging.adapter.in.mapper;

import br.gabrielsmartins.healthservice.application.domain.Measurement;
import br.gabrielsmartins.healthservice.application.domain.Person;
import br.gabrielsmartins.schemas.measurement_collected.MeasurementCollected;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MeasurementConsumerMapper {

    public Measurement mapToDomain(MeasurementCollected message){
        var mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<MeasurementCollected, Measurement>() {
            @Override
            protected void configure() {
              using((Converter<String, Person>) context -> {
                  var person = new Person();
                  UUID id = context.getSource() == null ? null : UUID.fromString(context.getSource());
                  person.setId(id);
                  return person;
              }).map(this.source.getPersonId(), this.destination.getPerson());
              skip(this.destination.getId());
            }
        });
        return mapper.map(message, Measurement.class);
    }
}
