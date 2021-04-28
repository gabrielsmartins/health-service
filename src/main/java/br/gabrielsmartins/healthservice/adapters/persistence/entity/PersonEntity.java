package br.gabrielsmartins.healthservice.adapters.persistence.entity;

import br.gabrielsmartins.healthservice.adapters.persistence.entity.enums.GenderData;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Data
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
@Table("tbl_person")
public class PersonEntity implements Serializable {

    @Id
    @Column("person_id")
    private UUID id;

    @Column("person_name")
    private String name;

    @Column("person_last_name")
    private String lastName;

    @Column("person_dob")
    private LocalDate dob;

    @Column("person_gender")
    private GenderData gender;

}
