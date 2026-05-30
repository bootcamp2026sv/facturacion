package com.bootcamp.facturacion.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO para transferencia de datos de comercio")
public class ComercioDTO {
    @Schema(description = "ID del comercio", example = "1")
    private Long id;

    @Schema(description = "NIT del comercio", example = "06141234590001", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nit;

    @Schema(description = "NRC del comercio", example = "123456-7", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nrc;

    @Schema(description = "Nombre del comercio", example = "Comercio S.A. de C.V.", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombre;

    @Schema(description = "Nombre comercial", example = "Comercio")
    private String nombreComercial;

    @Schema(description = "Teléfono", example = "22000000")
    private String telefono;

    @Schema(description = "Correo electrónico", example = "comercio@correo.com")
    private String correo;

    @Schema(description = "Es gran contribuyente", example = "false")
    private boolean granContribuyente;

    @Schema(description = "Complemento de dirección", example = "Col. Las Brisas")
    private String complementoDireccion;

    @Schema(description = "Tipo de establecimiento: 2 = casa matriz, 1 = sucursal", example = "2")
    private int tipoEstablecimiento;

    @Schema(description = "Código de establecimiento MH", example = "M001")
    private String codEstableMH;

    @Schema(description = "Código de punto de venta MH", example = "P001")
    private String codPuntoVentaMH;

    @Schema(description = "ID del municipio", example = "1")
    private Long municipio_id;

    @Schema(description = "ID de la actividad económica", example = "1")
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
