package br.gabrielsmartins.healthservice.application.service.analizers;

import br.gabrielsmartins.healthservice.application.domain.enums.MeasurementType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class MeasurementAnalyzerFactory {

    private final Map<MeasurementType, MeasurementAnalyzer> analyzersMap;

    public MeasurementAnalyzerFactory(List<MeasurementAnalyzer> analyzers){
        this.analyzersMap = build(analyzers);
    }

    public MeasurementAnalyzer createAnalyzer(MeasurementType type){
      return this.analyzersMap.get(type);
    }

    private Map<MeasurementType, MeasurementAnalyzer> build(List<MeasurementAnalyzer> analyzers){
        return analyzers.stream()
                        .collect(Collectors.toMap(MeasurementAnalyzer::getMeasurementType, Function.identity()));
    }

}
