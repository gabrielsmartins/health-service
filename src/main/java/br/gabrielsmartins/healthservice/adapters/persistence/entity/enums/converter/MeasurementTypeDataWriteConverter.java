package br.gabrielsmartins.healthservice.adapters.persistence.entity.enums.converter;

import br.gabrielsmartins.healthservice.adapters.persistence.entity.enums.MeasurementTypeData;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.r2dbc.core.Parameter;

@ReadingConverter
public class MeasurementTypeDataWriteConverter implements Converter<MeasurementTypeData, OutboundRow> {

    @Override
    public OutboundRow convert(MeasurementTypeData source) {
        var row = new OutboundRow();
        row.put("measurement_type_id", Parameter.from(source.getCode()));
        return row;
    }
}
