package br.gabrielsmartins.healthservice.application.support;

import br.gabrielsmartins.healthservice.application.domain.Measurement;
import br.gabrielsmartins.healthservice.application.domain.enums.MeasurementType;

import java.time.LocalDateTime;

public class MeasurementSupport {

    private MeasurementSupport(){
        super();
    }

    public static Measurement.MeasurementBuilder defaultMeasurement(){
        return Measurement.builder()
                          .withMeasuredAt(LocalDateTime.now())
                          .withType(MeasurementType.HEART_RATE)
                          .withValue(60);
    }
}
