package com.bootcamp.facturacion.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "catUnidadesDeMedida")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Catálogo de unidades de medida")
public class UnidadDeMedida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID autogenerado", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long Id;

    @Column(name = "codigo", nullable = false)
    @Schema(description = "Código de la unidad de medida", example = "1")
    private int codUnidad;

    @Column(name = "descripcion", nullable = false)
    @Schema(description = "Descripción de la unidad de medida", example = "Unidad")
    private String descUnidad;
}
