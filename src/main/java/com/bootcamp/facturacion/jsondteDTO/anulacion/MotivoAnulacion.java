package com.bootcamp.facturacion.jsondteDTO.anulacion;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO sección motivo para evento de anulación")
public class MotivoAnulacion {
    private int tipoAnulacion;
    private String motivoAnulacion;
    private String nombreResponsable;
    private String tipDocResponsable;
    private String numDocResponsable;
    private String nombreSolicita;
    private String tipDocSolicita;
    private String numDocSolicita;
}
