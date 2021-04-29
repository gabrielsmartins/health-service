package br.gabrielsmartins.healthservice.adapters.messaging.adapter.out.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.gabrielsmartins.healthservice.application.support.MeasurementSupport.defaultMeasurement;
import static br.gabrielsmartins.healthservice.application.support.PersonSupport.defaultPerson;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MeasurementProducerMapperTest {

    private MeasurementProducerMapper mapper;

    @BeforeEach
    public void setup(){
        this.mapper = new MeasurementProducerMapper();
    }

    @Test
    @DisplayName("Given Measurement When Map Then Return Message")
    public void givenMeasurementWhenMapThenReturnMessage(){

        var person = defaultPerson().build();

        var measurement = defaultMeasurement().build();
        measurement.setPerson(person);

        var message = this.mapper.mapToMessage(measurement);

        assertThat(message).hasNoNullFieldsOrProperties();
        assertThat(message.getPersonId()).isEqualTo(measurement.getPerson().getId().toString());
        assertThat(message.getMeasuredAt()).isEqualTo(measurement.getMeasuredAt());
        assertThat(message.getMeasurementType().name()).isEqualTo(measurement.getType().name());
        assertThat(message.getValue()).isEqualTo(measurement.getValue());
        assertThat(message.getAnalyzedAt()).isEqualTo(measurement.getAnalyzedAt());
        assertThat(message.getMeasurementClassification().name()).isEqualTo(measurement.getClassification().name());
    }
}
