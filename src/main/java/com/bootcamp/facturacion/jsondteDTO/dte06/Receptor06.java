package com.bootcamp.facturacion.jsondteDTO.dte06;

import com.bootcamp.facturacion.jsondteDTO.DireccionV2DTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO sección receptor dte 06")
public class Receptor06 {
    private String nrc;
    private String nombre;
    private String codActividad;
    private String descActividad;
    private String nombreComercial;
    private DireccionV2DTO direccion;
    private String telefono;
    private String correo;
    private String tipoDocumento;
    private String numDocumento;
}
