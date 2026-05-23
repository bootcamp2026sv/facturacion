package com.bootcamp.facturacion.dto;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComercioDTO {
    private Long id;
    private String nit;
    private String nrc;
    private String nombre;
    private String nombreComercial;
    private String telefono;
    private String correo;
    private boolean granContribuyente;
    private String complementoDireccion;
    private int tipoEstablecimiento;
    private String codEstableMH;
    private String codPuntoVentaMH;
    private Long municipio_id;
    private Long actividadEconomica_id;
}
/*
{
	"id": 1,
	"tipoDocumento": 13,
	"numDocumento": "038780392",
	"nrc": "3214545",
	"nombre": "Josue",
	"apellidos": "Guardado",
	"nombreComercial": "Devs El Salvador",
	"telefono": "70000000",
	"correo": "correo@gmail.com",
	"granContribuyente": false,
	"complementoDireccion": "Col. Las Brisas",
	"activo": true,
	"municipio_id":1,
	"actividadEconomica_id":1
}

* */
