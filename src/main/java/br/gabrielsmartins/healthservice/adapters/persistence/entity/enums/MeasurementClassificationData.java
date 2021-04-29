package br.gabrielsmartins.healthservice.adapters.persistence.entity.enums;

import br.gabrielsmartins.healthservice.application.domain.enums.MeasurementClassification;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
public enum MeasurementClassificationData {

    NORMAL('N', MeasurementClassification.NORMAL),
    HIGH('H', MeasurementClassification.HIGH),
    LOW('L', MeasurementClassification.LOW);

    private final Character prefix;
    private final MeasurementClassification source;

    public static MeasurementClassificationData fromPrefix(Character prefix){
        return Stream.of(MeasurementClassificationData.values())
                .filter(classification -> classification.getPrefix().equals(prefix))
                .findFirst()
                .orElse(null);
    }

    public static MeasurementClassificationData fromSource(MeasurementClassification source){
        return Stream.of(MeasurementClassificationData.values())
                .filter(classification -> classification.getSource() == source)
                .findFirst()
                .orElse(null);
    }
}
