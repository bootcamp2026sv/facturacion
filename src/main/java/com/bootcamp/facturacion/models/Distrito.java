package com.bootcamp.facturacion.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "distritos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Entidad que representa un distrito")
public class Distrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "ID autogenerado", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long Id;

    @Column(name = "codigo", nullable = false)
    @Schema(description = "Código del distrito", example = "01")
    private String Codigo;

    @Column(name = "nombre", nullable = false)
    @Schema(description = "Nombre del distrito", example = "San Salvador")
    private String Nombre;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "municipio_id", nullable = false)
    @Schema(description = "Municipio al que pertenece")
    private Municipio municipio;

}