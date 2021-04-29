package br.gabrielsmartins.healthservice.application.service.analizers;

import br.gabrielsmartins.healthservice.application.domain.Measurement;
import br.gabrielsmartins.healthservice.application.domain.enums.MeasurementType;
import br.gabrielsmartins.healthservice.application.ports.in.SaveMeasurementUseCase;
import org.springframework.stereotype.Component;

@Component
public class OxygenSaturationMeasurementAnalyzer extends MeasurementAnalyzer {

    public OxygenSaturationMeasurementAnalyzer(SaveMeasurementUseCase saveMeasurementUseCase) {
        super(saveMeasurementUseCase);
    }

    @Override
    protected boolean isHighLevel(Measurement measurement) {
        return measurement.getValue() > 100;
    }

    @Override
    protected boolean isLowLevel(Measurement measurement) {
        return measurement.getValue() < 95;
    }

    @Override
    protected MeasurementType getMeasurementType() {
        return MeasurementType.OXYGEN_SATURATION;
    }
}
