package br.gabrielsmartins.healthservice.adapters.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
@Table("tbl_measurement")
public class MeasurementEntity implements Serializable {

    @Id
    @Column("measurement_id")
    private Long id;

    @Column("measurement_person_id")
    private UUID personId;

    @Column("measurement_type_id")
    private Integer type;

    @Column("measured_at")
    private LocalDateTime measuredAt;

    @Column("measurement_value")
    private Double value;

}
