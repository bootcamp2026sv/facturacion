package com.bootcamp.facturacion.jsondteDTO.dte03v4;

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
@Schema(description = "DTO sección identificacion dte 03 v4")
public class Identificacion03 {

    @Builder.Default
    private int version = 4;
    
    private String ambiente; // "00" pruebas o "01" produccion
    
    @Builder.Default
    private String tipoDte = "03";
    
    private String numeroControl;
    private String codigoGeneracion;
    
    @Builder.Default
    private int tipoModelo = 1;
    
    @Builder.Default
    private int tipoOperacion = 1;
    
    private Integer tipoContingencia;
    private String motivoContin;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecEmi;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime horEmi;

    @Builder.Default
    private String tipoMoneda = "USD";
}
