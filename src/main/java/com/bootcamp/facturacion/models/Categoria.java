package com.bootcamp.facturacion.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "categorias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Entidad que representa una categoría de producto")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "ID autogenerado de la categoría", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long Id;

    @Column(name = "nombre", nullable = false, unique = true)
    @Schema(description = "Nombre de la categoría", example = "Electrónica")
    private String nombre;

    @Column(name = "descripcion", nullable = true)
    @Schema(description = "Descripción de la categoría", example = "Dispositivos electrónicos y accesorios")
    private String descripcion;

    @Column(name = "activo", nullable = false)
    @Builder.Default
    @Schema(description = "Indica si la categoría está activa", example = "true")
    private boolean activo = true;
}
