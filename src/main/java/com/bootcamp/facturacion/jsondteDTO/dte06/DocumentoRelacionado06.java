package com.bootcamp.facturacion.jsondteDTO.dte06;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentoRelacionado06 {
    private String tipoDocumento;
    private int tipoGeneracion; // 1 = Electrónico, 2 = Físico
    private String numeroDocumento;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaEmision;
}
