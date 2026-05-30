package com.bootcamp.facturacion.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "municipios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Entidad que representa un municipio")
public class Municipio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID autogenerado", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long Id;

    @Column(name = "codigo", nullable = false)
    @Schema(description = "Código del municipio", example = "01")
    private String Codigo;

    @Column(name = "nombre", nullable = false)
    @Schema(description = "Nombre del municipio", example = "San Salvador")
    private String Nombre;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "departamento_id", nullable = false)
    @Schema(description = "Departamento al que pertenece")
    private Departamento departamento;

}