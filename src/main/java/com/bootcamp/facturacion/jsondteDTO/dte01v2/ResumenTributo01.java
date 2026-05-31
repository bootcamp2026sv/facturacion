package com.bootcamp.facturacion.jsondteDTO.dte01v2;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumenTributo01 {
    private String codigo;
    private String descripcion;
    private BigDecimal valor;
}
