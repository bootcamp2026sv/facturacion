package com.bootcamp.facturacion.jsondteDTO.dte05;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumenTributo05 {
    private String codigo;
    private String descripcion;
    private BigDecimal valor;
}
