package com.bootcamp.facturacion.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO para transferencia de datos de usuario")
public class UsuarioDTO {

    @Schema(description = "ID del usuario", example = "1")
    private Long id;

    @Schema(description = "Nombre de usuario", example = "jperez", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombreUsuario;

    @Schema(description = "Correo electrónico", example = "jperez@correo.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String correo;

    @Schema(description = "Contraseña", example = "miPassword123", requiredMode = Schema.RequiredMode.REQUIRED)
    private String contrasena;

    @Schema(description = "Usuario habilitado", example = "true")
    private boolean habilitado;

}

