package com.bootcamp.facturacion.jsondteDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO sección interna direccion dte v2")
public class DireccionV2DTO {
     private String departamento;
     private String municipio;
     private String distrito;
     private String complemento;
}
