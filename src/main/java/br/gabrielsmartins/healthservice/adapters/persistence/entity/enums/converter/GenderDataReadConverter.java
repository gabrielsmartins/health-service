package br.gabrielsmartins.healthservice.adapters.persistence.entity.enums.converter;

import br.gabrielsmartins.healthservice.adapters.persistence.entity.enums.GenderData;
import io.r2dbc.spi.Row;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class GenderDataReadConverter implements Converter<Row, GenderData>  {

    @Override
    public GenderData convert(Row source) {
        Character value = source.get("person_gender", Character.class);
        return GenderData.fromPrefix(value);
    }

}
