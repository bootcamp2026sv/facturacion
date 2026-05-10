package com.bootcamp.facturacion.models;

public class Comercio {

    private Long Id;
    private String nit; // permitir 9 o 14 caracteres
    private String nrc;// no nulo
    private String nombre; // no nulo
    private String nombreComercial;//no nulo
    private String tipoEstablecimiento;//2 casa matriz 1 sucursal (ENUM)
    private String telefono;// sin guiones 8 caracteres
    private String codEstableMH;// M001 / S00X  codigo de sucursales
    private String codEstable; // Lo mismo que EstableMH
    private String codPuntoVentaMH; // P00X
    private String codPuntoVenta; // lo mismo que VentaMH
    private String correo;// email valido
    public Direccion direccion;

}
