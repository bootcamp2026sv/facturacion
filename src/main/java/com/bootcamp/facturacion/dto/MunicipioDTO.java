package com.bootcamp.facturacion.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO para transferencia de datos de municipio")
public class MunicipioDTO {
    String codigo;
    String nombre;
    Long departamento_id;
}
