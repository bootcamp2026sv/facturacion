package com.bootcamp.facturacion.jsondteDTO.dte01v2;

import com.bootcamp.facturacion.jsondteDTO.DireccionV2DTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO sección emisor dte 01 v2")
public class Emisor01 {

    private String nit;
    private String nrc;
    private String nombre;
    private String codActividad;
    private String descActividad;
    private String nombreComercial;
    private String telefono;
    private String codEstable;
    private String codPuntoVenta;
    private String correo;
    private DireccionV2DTO direccion;

}
