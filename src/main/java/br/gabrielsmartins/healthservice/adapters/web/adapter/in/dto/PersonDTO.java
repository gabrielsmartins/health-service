package br.gabrielsmartins.healthservice.adapters.web.adapter.in.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @JsonProperty(value = "first_name", required = true)
    @NotBlank
    private String firstName;

    @JsonProperty(value = "last_name", required = true)
    @NotBlank
    private String lastName;

    @JsonProperty(value = "date_of_birthday", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate dob;

    @JsonProperty(value = "gender", required = true)
    @Pattern(regexp = "M|F")
    private String gender;

}
