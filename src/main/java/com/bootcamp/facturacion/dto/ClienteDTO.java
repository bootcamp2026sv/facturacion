package com.bootcamp.facturacion.dto;

import com.bootcamp.facturacion.models.ActividadEconomica;
import com.bootcamp.facturacion.models.Municipio;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDTO {
    private int id;
    private int tipoDocumento;
    private String numDocumento;
    private String nrc;
    private String nombre;
    private String apellidos;
    private String nombreComercial;
    private String telefono;
    private String correo;
    private boolean granContribuyente;
    private String complementoDireccion;
    private int municipio_id;
    private int actividadEconomica_id;
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
