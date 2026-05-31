package com.bootcamp.facturacion.jsondteDTO.anulacion;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO sección documento (DTE a anular) para evento de anulación")
public class DocumentoAnulacion {
    private String tipoDte;
    private String codigoGeneracion;
    private String selloRecibido;
    private String numeroControl;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecEmi;

    private String codigoGeneracionR;
    private String tipoDocumento;
    private String numDocumento;
    private String nombre;
    private String telefono;
    private String correo;
}
