package br.gabrielsmartins.healthservice.application.support;

import br.gabrielsmartins.healthservice.application.domain.Measurement;
import br.gabrielsmartins.healthservice.application.domain.enums.MeasurementClassification;
import br.gabrielsmartins.healthservice.application.domain.enums.MeasurementType;

import java.time.LocalDateTime;

public class MeasurementSupport {

    private MeasurementSupport(){
        super();
    }

    public static Measurement.MeasurementBuilder defaultMeasurement(){
        return Measurement.builder()
                          .withId(1L)
                          .withMeasuredAt(LocalDateTime.now())
                          .withType(MeasurementType.HEART_RATE)
                          .withValue(60.00)
                          .withAnalyzedAt(LocalDateTime.now())
                          .withClassification(MeasurementClassification.NORMAL);
    }
}
