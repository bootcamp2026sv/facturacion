package com.bootcamp.facturacion.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthRequest {
    @Schema(description = "Nombre de usuario o correo electrónico", example = "admin")
    private String usernameOrEmail;

    @Schema(description = "Contraseña", example = "admin123")
    private String password;
}
