package com.bootcamp.facturacion.jsondteDTO.anulacion;

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
@Schema(description = "DTO sección identificacion para evento de anulación")
public class IdentificacionAnulacion {

    @Builder.Default
    private int version = 3;
    
    private String ambiente; // "00" pruebas o "01" produccion
    private String codigoGeneracion;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecEmi;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime horEmi;

    private Integer fusion;
}
