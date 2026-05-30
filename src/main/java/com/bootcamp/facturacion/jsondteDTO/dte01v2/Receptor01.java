package com.bootcamp.facturacion.jsondteDTO.dte01v2;

import com.bootcamp.facturacion.jsondteDTO.DireccionV2DTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO sección receptor dte 01 v2")
public class Receptor01 {
    private String tipoDocumento;
    private String numDocumento;
    private String nrc;
    private String nombre;
    private String codActividad;
    private String descActividad;
    private DireccionV2DTO direccion;
    private String telefono;
    private String correo;
}
