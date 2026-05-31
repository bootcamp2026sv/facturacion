package com.bootcamp.facturacion.jsondteDTO.dte03v4;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumenTributo03 {
    private String codigo;
    private String descripcion;
    private BigDecimal valor;
}
