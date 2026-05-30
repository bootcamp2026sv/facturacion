package com.bootcamp.facturacion.controllers.auth;

import com.bootcamp.facturacion.dto.*;
import com.bootcamp.facturacion.models.auth.TokenRefresh;
import com.bootcamp.facturacion.models.auth.Usuario;
import com.bootcamp.facturacion.security.JwtService;
import com.bootcamp.facturacion.services.auth.TokenRefreshService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/auth")
@Tag(name = "Autenticación", description = "Endpoints para Login, Logout y Refresco de Tokens")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private TokenRefreshService tokenRefreshService;

    @Operation(summary = "Iniciar sesión", description = "Valida credenciales y genera tokens de acceso y refresco")
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsernameOrEmail(), request.getPassword())
        );

        Usuario usuario = (Usuario) authentication.getPrincipal();
        String accessToken = jwtService.generateToken(usuario);
        TokenRefresh refreshToken = tokenRefreshService.createRefreshToken(usuario.getId());

        List<String> roles = usuario.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getToken())
                .username(usuario.getNombreUsuario())
                .email(usuario.getCorreo())
                .roles(roles)
                .build();
    }

    @Operation(summary = "Refrescar token", description = "Genera un nuevo token de acceso a partir de un refresh token válido")
    @PostMapping("/refresh")
    public TokenRefreshResponse refresh(@RequestBody TokenRefreshRequest request) {
        return tokenRefreshService.findByToken(request.getRefreshToken())
                .map(tokenRefreshService::verifyExpiration)
                .map(TokenRefresh::getUsuario)
                .map(usuario -> {
                    String accessToken = jwtService.generateToken(usuario);
                    TokenRefresh nuevoRefreshToken = tokenRefreshService.createRefreshToken(usuario.getId());
                    return TokenRefreshResponse.builder()
                            .accessToken(accessToken)
                            .refreshToken(nuevoRefreshToken.getToken())
                            .build();
                })
                .orElseThrow(() -> new RuntimeException("Token de refresco no encontrado en la base de datos"));
    }

    @Operation(summary = "Cerrar sesión", description = "Revoca el refresh token del usuario")
    @PostMapping("/logout")
    public String logout(@RequestBody TokenRefreshRequest request) {
        tokenRefreshService.findByToken(request.getRefreshToken())
                .ifPresent(token -> tokenRefreshService.deleteByUsuarioId(token.getUsuario().getId()));
        return "Sesión cerrada correctamente";
    }
}
