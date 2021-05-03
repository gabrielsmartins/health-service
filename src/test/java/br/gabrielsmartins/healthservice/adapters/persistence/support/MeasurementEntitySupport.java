package br.gabrielsmartins.healthservice.adapters.persistence.support;

import br.gabrielsmartins.healthservice.adapters.persistence.entity.MeasurementEntity;
import br.gabrielsmartins.healthservice.adapters.persistence.entity.enums.MeasurementClassificationData;
import br.gabrielsmartins.healthservice.adapters.persistence.entity.enums.MeasurementTypeData;

import java.time.LocalDateTime;

public class MeasurementEntitySupport {

    private MeasurementEntitySupport(){
        super();
    }

    public static MeasurementEntity.MeasurementEntityBuilder defaultMeasurementEntity(){
        return MeasurementEntity.builder()
                          .withId(1L)
                          .withMeasuredAt(LocalDateTime.now())
                          .withType(MeasurementTypeData.HEART_RATE.getCode())
                          .withValue(60.00)
                          .withAnalyzedAt(LocalDateTime.now())
                          .withClassification(MeasurementClassificationData.NORMAL.getPrefix().toString());
    }
}
