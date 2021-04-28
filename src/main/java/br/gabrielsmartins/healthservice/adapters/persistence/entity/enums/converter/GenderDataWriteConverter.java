package br.gabrielsmartins.healthservice.adapters.persistence.entity.enums.converter;

import br.gabrielsmartins.healthservice.adapters.persistence.entity.enums.GenderData;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.r2dbc.core.Parameter;

@WritingConverter
public class GenderDataWriteConverter implements Converter<GenderData, OutboundRow>  {

    @Override
    public OutboundRow convert(GenderData source) {
        var row = new OutboundRow();
        row.put("person_gender", Parameter.from(source.getPrefix()));
        return row;
    }
}
