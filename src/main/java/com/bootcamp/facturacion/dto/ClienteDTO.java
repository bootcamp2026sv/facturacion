package com.bootcamp.facturacion.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO para transferencia de datos de cliente")
public class ClienteDTO {
    @Schema(description = "ID del cliente", example = "1")
    private Long id;
    private int tipoDocumento;
    private String numDocumento;
    private String nrc;
    @Schema(description = "Nombre del cliente", example = "Juan Perez",requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombre;
    private String apellidos;
    private String nombreComercial;
    private String telefono;
    private String correo;
    private boolean granContribuyente;
    private boolean activo;
    private String complementoDireccion;
    private Long distrito_id;
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
