package com.bootcamp.facturacion.jsondteDTO.dte06;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumenTributo06 {
    private String codigo;
    private String descripcion;
    private BigDecimal valor;
}
