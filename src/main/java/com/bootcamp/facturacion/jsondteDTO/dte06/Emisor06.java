package com.bootcamp.facturacion.jsondteDTO.dte06;

import com.bootcamp.facturacion.jsondteDTO.DireccionV2DTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO sección emisor dte 06")
public class Emisor06 {
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
