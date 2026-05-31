package com.bootcamp.facturacion.jsondteDTO.dte01v2;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO sección identificacion dte 01 v2")
public class Identificacion01 {

    private int version=2;
    private String ambiente; // 00 pruebas ó 01 produccion
    private String tipoDte="01";
    private String numeroControl;
    private String codigoGeneracion;
    private int tipoModelo=1;
    private int tipoOperacion=1;
    private Integer tipoContingencia; //requiere null en ocaciones
    private String motivoContin;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecEmi;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime horEmi;

    private String tipoMoneda="USD";

}
