package com.bootcamp.facturacion.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Cliente{
    private Long Id;
    private int tipoDocumento; // 13 DUI 36 NIT 37 OTRO
    private String numDocumento;// sin guiones
    private String nrc;//sin guiones
    private String nombre;
    private String nombreComercial;
    private String telefono;
    private String correo;
    private boolean granContribuyente;

    @Column(name = "complemento", nullable = false)
    private String complemento;
    @JsonIgnore //solventar problema de recursion
    @ManyToOne
    @JoinColumn(name = "municipio_id", nullable = false)
    private Municipio municipio;
}
/*
* A considerar despues
*
  public String codActividad;
  public String descActividad;
  *
  *
* */
