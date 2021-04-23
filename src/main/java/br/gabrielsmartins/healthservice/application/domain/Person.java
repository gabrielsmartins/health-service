package br.gabrielsmartins.healthservice.application.domain;

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
    private String name;
    private String lastName;
    private LocalDate dob;
    private Gender gender;

}
