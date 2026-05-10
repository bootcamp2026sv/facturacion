package com.bootcamp.facturacion.models;

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
    public Direccion direccion;
}
/*
* A considerar despues
*
  public String codActividad;
  public String descActividad;
  *
  *
* */
