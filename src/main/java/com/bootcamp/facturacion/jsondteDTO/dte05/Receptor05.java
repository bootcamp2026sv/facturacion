package com.bootcamp.facturacion.jsondteDTO.dte05;

import com.bootcamp.facturacion.jsondteDTO.DireccionV2DTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO sección receptor dte 05")
public class Receptor05 {
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
