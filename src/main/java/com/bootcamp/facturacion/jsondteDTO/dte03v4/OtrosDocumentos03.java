package com.bootcamp.facturacion.jsondteDTO.dte03v4;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OtrosDocumentos03 {
    private int codDocAsociado;
    private String descDocumento;
    private String detalleDocumento;
}
