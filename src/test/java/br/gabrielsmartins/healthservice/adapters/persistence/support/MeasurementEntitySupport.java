package br.gabrielsmartins.healthservice.adapters.persistence.support;

import br.gabrielsmartins.healthservice.adapters.persistence.entity.MeasurementEntity;
import br.gabrielsmartins.healthservice.adapters.persistence.entity.enums.MeasurementTypeData;

import java.time.LocalDateTime;

public class MeasurementEntitySupport {

    private MeasurementEntitySupport(){
        super();
    }

    public static MeasurementEntity.MeasurementEntityBuilder defaultMeasurementEntity(){
        return MeasurementEntity.builder()
                          .withMeasuredAt(LocalDateTime.now())
                          .withType(MeasurementTypeData.HEART_RATE)
                          .withValue(60);
    }
}
