package com.bootcamp.facturacion.jsondteDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO sección interna pagos dte v2")
public class PagosV2DTO {
    private String codigo;
    private BigDecimal montoPago;
    private String referencia;
    private String periodo;
    private String plazo;
}
