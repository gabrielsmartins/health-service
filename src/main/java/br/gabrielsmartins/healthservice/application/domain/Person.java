package br.gabrielsmartins.healthservice.application.domain;

import br.gabrielsmartins.healthservice.application.domain.enums.Gender;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Data
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
public class Person {

    private UUID id;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private Gender gender;

}
