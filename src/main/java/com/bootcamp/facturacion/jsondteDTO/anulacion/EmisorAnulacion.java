package com.bootcamp.facturacion.jsondteDTO.anulacion;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO sección emisor para evento de anulación")
public class EmisorAnulacion {
    private String nit;
    private String nombre;
    private String codEstableMH;
    private String codEstable;
    private String codPuntoVentaMH;
    private String codPuntoVenta;
    private String telefono;
    private String correo;
}
