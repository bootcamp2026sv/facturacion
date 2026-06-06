package com.bootcamp.facturacion.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Entidad que representa un cliente del sistema")
public class Cliente{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "ID autogenerado", example = "1",
            accessMode = Schema.AccessMode.READ_ONLY)
    private Long Id;

    @Column(name = "tipoDocumento", nullable = true)
    @Schema(description = "Tipo de documento", example = "13") // 13 DUI 36 NIT 37 OTRO
    private int tipoDocumento;

    @Column(name = "numDocumento", nullable = true)
    private String numDocumento;// sin guiones

    @Column(name = "nrc", nullable = true)
    private String nrc;//sin guiones

    @Column(name = "nombres", nullable = false)
    private String nombre;

    @Column(name = "apellidos", nullable = true)
    private String apellidos;

    @Column(name = "nombreComercial", nullable = true)
    private String nombreComercial;

    @Column(name = "telefono", nullable = true)
    private String telefono;

    @Column(name = "correo", nullable = true)
    private String correo;

    @Column(name = "granContribuyente", nullable = false)
    private boolean granContribuyente;

    @Column(name = "complemento", nullable = true)
    private String complementoDireccion;

    @Column(name = "activo", nullable = false)
    private boolean activo; // soft delete


    @ManyToOne
    @JoinColumn(name = "distrito_id", nullable = true)
    private Distrito distrito;


    @ManyToOne
    @JoinColumn(name = "actividadEconomica_id", nullable = true)
    private ActividadEconomica actividadEconomica;
}
