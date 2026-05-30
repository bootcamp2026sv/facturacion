package com.bootcamp.facturacion.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenRefreshRequest {
    private String refreshToken;
}
