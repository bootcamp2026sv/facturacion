package com.bootcamp.facturacion.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "comercios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Entidad que representa un comercio")
public class Comercio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID autogenerado", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long Id;

    @Column(name = "nit", nullable = false)
    @Schema(description = "NIT del comercio (9 o 14 dígitos)", example = "06141234590001")
    private String nit;

    @Column(name = "nrc", nullable = false)
    @Schema(description = "NRC del comercio", example = "123456-7")
    private String nrc;

    @Column(name = "nombre", nullable = false)
    @Schema(description = "Nombre del comercio", example = "Comercio S.A. de C.V.")
    private String nombre;

    @Column(name = "nombreComercial", nullable = false)
    @Schema(description = "Nombre comercial", example = "Comercio")
    private String nombreComercial;

    @Column(name = "tipoEstablecimiento", nullable = false)
    @Schema(description = "Tipo de establecimiento: 2 = casa matriz, 1 = sucursal", example = "2")
    private int tipoEstablecimiento;

    @Column(name = "telefono", nullable = false)
    @Schema(description = "Teléfono (8 dígitos)", example = "22000000")
    private String telefono;

    @Column(name = "codEstableMH", nullable = false)
    @Schema(description = "Código de establecimiento MH", example = "M001")
    private String codEstableMH;

    @Column(name = "codPuntoVentaMH", nullable = false)
    @Schema(description = "Código de punto de venta MH", example = "P001")
    private String codPuntoVentaMH;

    @Column(name = "correo", nullable = false)
    @Schema(description = "Correo electrónico", example = "comercio@correo.com")
    private String correo;

    @Column(name = "granContribuyente", nullable = false)
    @Schema(description = "Es gran contribuyente", example = "false")
    private boolean granContribuyente;

    @Column(name = "complemento", nullable = true)
    @Schema(description = "Complemento de dirección", example = "Col. Las Brisas")
    private String complementoDireccion;

    @ManyToOne
    @JoinColumn(name = "municipio_id", nullable = true)
    @Schema(description = "Municipio del comercio")
    private Municipio municipio;

    @ManyToOne
    @JoinColumn(name = "actividadEconomica_id", nullable = true)
    @Schema(description = "Actividad económica del comercio")
    private ActividadEconomica actividadEconomica;

}
