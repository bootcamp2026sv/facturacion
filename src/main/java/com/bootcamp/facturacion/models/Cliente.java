package com.bootcamp.facturacion.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "tipoDocumento", nullable = true)
    private int tipoDocumento; // 13 DUI 36 NIT 37 OTRO

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
    @JoinColumn(name = "municipio_id", nullable = true)
    private Municipio municipio;


    @ManyToOne
    @JoinColumn(name = "actividadEconomica_id", nullable = true)
    private ActividadEconomica actividadEconomica;
}
