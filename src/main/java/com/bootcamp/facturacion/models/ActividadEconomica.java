package com.bootcamp.facturacion.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "catActividadesEconomicas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Catálogo de actividades económicas")
public class ActividadEconomica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID autogenerado", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long Id;

    @Column(name = "codActividad", nullable = false, unique = true)
    @Schema(description = "Código de la actividad económica", example = "47100")
    private String codActividad;

    @Column(name = "descActividad", nullable = false)
    @Schema(description = "Descripción de la actividad económica", example = "Venta al por menor")
    private String descActividad;

    @Column(name = "activo", nullable = false)
    @Schema(description = "Está activo (soft delete)", example = "true")
    private boolean activo;
}
