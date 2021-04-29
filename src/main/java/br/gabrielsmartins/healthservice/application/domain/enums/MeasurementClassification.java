package br.gabrielsmartins.healthservice.application.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
public enum MeasurementClassification {

    NORMAL('N', "NORMAL"),
    HIGH('H', "HIGH"),
    LOW('L', "LOW");

    private final Character prefix;
    private final String description;

    public static MeasurementClassification fromPrefix(Character prefix){
        return Stream.of(MeasurementClassification.values())
                .filter(classification -> classification.getPrefix().equals(prefix))
                .findFirst()
                .orElse(null);
    }

    public static MeasurementClassification fromDescription(String description){
        return Stream.of(MeasurementClassification.values())
                .filter(classification -> classification.getDescription().equals(description))
                .findFirst()
                .orElse(null);
    }
}
