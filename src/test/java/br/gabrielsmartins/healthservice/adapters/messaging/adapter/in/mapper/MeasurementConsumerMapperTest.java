package br.gabrielsmartins.healthservice.adapters.messaging.adapter.in.mapper;

import br.gabrielsmartins.healthservice.application.domain.enums.MeasurementType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.gabrielsmartins.healthservice.adapters.messaging.adapter.support.MeasurementMessageSupport.defaultMeasurementMessage;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MeasurementConsumerMapperTest {

    private MeasurementConsumerMapper mapper;

    @BeforeEach
    public void setup(){
        this.mapper = new MeasurementConsumerMapper();
    }

    @Test
    @DisplayName("Given Message When Map Then Return Measurement")
    public void givenMessageWhenMapThenReturnMeasurement(){

        var message = defaultMeasurementMessage().build();

        var measurement = this.mapper.mapToDomain(message);

        assertThat(measurement).hasNoNullFieldsOrPropertiesExcept("analyzedAt", "classification");
        assertThat(measurement.getPerson().getId().toString()).isEqualTo(message.getPersonId());
        assertThat(measurement.getMeasuredAt()).isEqualTo(message.getMeasuredAt());
        assertThat(measurement.getType()).isEqualTo(MeasurementType.valueOf(message.getMeasurementType().name()));
        assertThat(measurement.getValue()).isEqualTo(message.getValue());
    }

}
