package com.bootcamp.facturacion.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "comercios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comercio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "nit", nullable = false)
    private String nit; // permitir 9 o 14 caracteres

    @Column(name = "nrc", nullable = false)
    private String nrc;// no nulo

    @Column(name = "nombre", nullable = false)
    private String nombre; // no nulo

    @Column(name = "nombreComercial", nullable = false)
    private String nombreComercial;//no nulo

    @Column(name = "tipoEstablecimiento", nullable = false)
    private int tipoEstablecimiento;//2 casa matriz 1 sucursal (ENUM)

    @Column(name = "telefono", nullable = false)
    private String telefono;// sin guiones 8 caracteres

    @Column(name = "codEstableMH", nullable = false)
    private String codEstableMH;// M001 / S00X  codigo de sucursales

   /* @Column(name = "codEstable", nullable = false)
    private String codEstable; // Lo mismo que EstableMH */

    @Column(name = "codPuntoVentaMH", nullable = false)
    private String codPuntoVentaMH; // P00X

   /* @Column(name = "codPuntoVenta", nullable = false)
    private String codPuntoVenta; // lo mismo que VentaMH */

    @Column(name = "correo", nullable = false)
    private String correo;// email valido

    @Column(name = "granContribuyente", nullable = false)
    private boolean granContribuyente;

    @Column(name = "complemento", nullable = true)
    private String complementoDireccion;

    @JsonIgnore //solventar problema de recursion
    @ManyToOne
    @JoinColumn(name = "municipio_id", nullable = true)
    private Municipio municipio;

    @JsonIgnore //solventar problema de recursion
    @ManyToOne
    @JoinColumn(name = "actividadEconomica_id", nullable = true)
    private ActividadEconomica actividadEconomica;

}
