package br.gabrielsmartins.healthservice.application.domain;

import br.gabrielsmartins.healthservice.application.domain.enums.MeasurementClassification;
import br.gabrielsmartins.healthservice.application.domain.enums.MeasurementType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class Measurement {

    private Long id;
    private Person person;
    private MeasurementType type;
    private LocalDateTime measuredAt;
    private Double value;
    private LocalDateTime analyzedAt;
    private MeasurementClassification classification;

}
