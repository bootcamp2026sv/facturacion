package com.bootcamp.facturacion.jsondteDTO.dte03v4;

import com.bootcamp.facturacion.jsondteDTO.DireccionV2DTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO sección receptor dte 03 v4")
public class Receptor03 {
    private String nit;
    private String nrc;
    private String nombre;
    private String codActividad;
    private String descActividad;
    private String nombreComercial;
    private DireccionV2DTO direccion;
    private String telefono;
    private String correo;
}
