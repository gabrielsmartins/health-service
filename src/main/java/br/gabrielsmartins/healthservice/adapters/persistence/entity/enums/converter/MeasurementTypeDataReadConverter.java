package br.gabrielsmartins.healthservice.adapters.persistence.entity.enums.converter;

import br.gabrielsmartins.healthservice.adapters.persistence.entity.enums.MeasurementTypeData;
import io.r2dbc.spi.Row;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class MeasurementTypeDataReadConverter implements Converter<Row, MeasurementTypeData> {

    @Override
    public MeasurementTypeData convert(Row source) {
        Integer value = source.get("measurement_type_id", Integer.class);
        return MeasurementTypeData.fromCode(value);
    }

}
