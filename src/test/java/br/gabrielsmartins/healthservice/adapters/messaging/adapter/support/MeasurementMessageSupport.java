package br.gabrielsmartins.healthservice.adapters.messaging.adapter.support;

import br.gabrielsmartins.schemas.measurement_received.MeasurementReceived;
import br.gabrielsmartins.schemas.measurement_received.MeasurementType;

import java.time.LocalDateTime;
import java.util.UUID;

public class MeasurementMessageSupport {

    private MeasurementMessageSupport(){
        super();
    }

    public static MeasurementReceived.Builder defaultMeasurementMessage(){
        return MeasurementReceived.newBuilder()
                                  .setPersonId(UUID.randomUUID().toString())
                                  .setMeasurementType(MeasurementType.HEART_RATE)
                                  .setValue(100)
                                  .setMeasuredAt(LocalDateTime.now());
    }
}
