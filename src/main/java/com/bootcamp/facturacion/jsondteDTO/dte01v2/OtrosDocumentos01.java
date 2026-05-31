package com.bootcamp.facturacion.jsondteDTO.dte01v2;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OtrosDocumentos01 {
    private int codDocAsociado;
    private String descDocumento;
    private String detalleDocumento;
}
