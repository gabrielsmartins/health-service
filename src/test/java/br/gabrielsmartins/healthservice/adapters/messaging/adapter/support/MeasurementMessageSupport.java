package br.gabrielsmartins.healthservice.adapters.messaging.adapter.support;

import br.gabrielsmartins.schemas.measurement_collected.MeasurementCollected;
import br.gabrielsmartins.schemas.measurement_collected.MeasurementType;

import java.time.LocalDateTime;
import java.util.UUID;

public class MeasurementMessageSupport {

    private MeasurementMessageSupport(){
        super();
    }

    public static MeasurementCollected.Builder defaultMeasurementMessage(){
        return MeasurementCollected.newBuilder()
                                  .setPersonId(UUID.randomUUID().toString())
                                  .setMeasurementType(MeasurementType.HEART_RATE)
                                  .setValue(100)
                                  .setMeasuredAt(LocalDateTime.now());
    }
}
