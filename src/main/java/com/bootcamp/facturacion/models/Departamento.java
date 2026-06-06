package com.bootcamp.facturacion.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "departamentos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Entidad que representa un departamento")
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "ID autogenerado", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long Id;

    @Column(name = "codigo", nullable = false)
    @Schema(description = "Código del departamento", example = "01")
    private String Codigo;

    @Column(name = "nombre", nullable = false)
    @Schema(description = "Nombre del departamento", example = "San Salvador")
    private String Nombre;

    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL, orphanRemoval = false,fetch = FetchType.EAGER)
    @Schema(description = "Lista de municipios del departamento")
    private List<Municipio> municipios = new ArrayList<>();
}
