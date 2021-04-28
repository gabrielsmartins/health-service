package br.gabrielsmartins.healthservice.adapters.persistence.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.gabrielsmartins.healthservice.adapters.persistence.support.MeasurementEntitySupport.defaultMeasurementEntity;
import static br.gabrielsmartins.healthservice.application.support.MeasurementSupport.defaultMeasurement;
import static br.gabrielsmartins.healthservice.application.support.PersonSupport.defaultPerson;
import static org.assertj.core.api.Assertions.assertThat;

public class MeasurementPersistenceMapperTest {

    private MeasurementPersistenceMapper mapper;

    @BeforeEach
    public void setup(){
        this.mapper = new MeasurementPersistenceMapper();
    }

    @Test
    @DisplayName("Given Measurement When Map Then Return Measurement Entity")
    public void givenMeasurementWhenMapThenReturnMeasurementEntity(){
        var person = defaultPerson().build();

        var measurement = defaultMeasurement()
                                                .withPerson(person)
                                                .build();

        var measurementEntity = this.mapper.mapToEntity(measurement);

        assertThat(measurementEntity).hasNoNullFieldsOrPropertiesExcept("id");
        assertThat(measurementEntity.getId()).isNull();
        assertThat(measurementEntity.getPersonId()).isEqualTo(measurement.getPerson().getId());
        assertThat(measurementEntity.getMeasuredAt()).isEqualTo(measurement.getMeasuredAt());
        assertThat(measurementEntity.getType().getSource()).isEqualTo(measurement.getType());
        assertThat(measurementEntity.getValue()).isEqualTo(measurement.getValue());
    }

    @Test
    @DisplayName("Given Measurement Entity When Map Then Return Measurement")
    public void givenMeasurementEntityWhenMapThenReturnMeasurement(){

        var measurementEntity = defaultMeasurementEntity().build();

        var measurement = this.mapper.mapToDomain(measurementEntity);

        assertThat(measurement).hasNoNullFieldsOrPropertiesExcept("person");
        assertThat(measurement.getPerson()).isNull();
        assertThat(measurement.getMeasuredAt()).isEqualTo(measurementEntity.getMeasuredAt());
        assertThat(measurement.getType()).isEqualTo(measurementEntity.getType().getSource());
        assertThat(measurement.getValue()).isEqualTo(measurementEntity.getValue());
    }
}
