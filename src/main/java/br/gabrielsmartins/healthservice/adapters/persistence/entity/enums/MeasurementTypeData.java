package br.gabrielsmartins.healthservice.adapters.persistence.entity.enums;

import br.gabrielsmartins.healthservice.application.domain.enums.MeasurementType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum MeasurementTypeData {

    HEART_RATE(1, MeasurementType.HEART_RATE),
    OXYGEN_SATURATION(2, MeasurementType.OXYGEN_SATURATION),
    BLOOD_GLUCOSE(3, MeasurementType.BLOOD_GLUCOSE),
    BLOOD_PRESSURE(4, MeasurementType.BLOOD_PRESSURE),
    TEMPERATURE(5, MeasurementType.TEMPERATURE);

    private final Integer code;
    private final MeasurementType source;

    public static MeasurementTypeData fromCode(Integer code) {
        return Stream.of(MeasurementTypeData.values())
                .filter(measurementTypeData -> measurementTypeData.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }

    public static MeasurementTypeData fromSource(MeasurementType source) {
        return Stream.of(MeasurementTypeData.values())
                .filter(measurementTypeData -> measurementTypeData.getSource() == source)
                .findFirst()
                .orElse(null);
    }

}
