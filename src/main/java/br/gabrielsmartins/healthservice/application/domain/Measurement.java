package br.gabrielsmartins.healthservice.application.domain;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class Measurement {

    private Person person;
    private MeasurementType type;
    private LocalDateTime measuredAt;
    private Number value;

}
