package br.gabrielsmartins.healthservice.adapters.web.adapter.in.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorDTO {

    @JsonProperty("code")
    private Integer code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("fields")
    private final List<ErrorFieldDTO> fields = new LinkedList<>();

    public Integer addField(ErrorFieldDTO field){
        this.fields.add(field);
        return this.fields.size();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder(setterPrefix = "with")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class ErrorFieldDTO{

        @JsonProperty("name")
        private String name;

        @JsonProperty("value")
        private Object value;

        @JsonProperty("message")
        private String message;

    }
}