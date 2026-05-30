package com.bootcamp.facturacion.services.auth;

import com.bootcamp.facturacion.models.auth.TokenRefresh;
import com.bootcamp.facturacion.models.auth.Usuario;
import com.bootcamp.facturacion.repository.TokenRefreshRepository;
import com.bootcamp.facturacion.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class TokenRefreshService {

    @Value("${security.jwt.refresh-expiration-time:604800000}") // 7 días en milisegundos
    private Long refreshExpirationMs;

    @Autowired
    private TokenRefreshRepository tokenRefreshRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<TokenRefresh> findByToken(String token) {
        return tokenRefreshRepository.findByToken(token);
    }

    @Transactional
    public TokenRefresh createRefreshToken(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + usuarioId));

        // Eliminar token anterior de este usuario para que no se acumulen
        tokenRefreshRepository.deleteByUsuario(usuario);

        TokenRefresh refreshToken = TokenRefresh.builder()
                .usuario(usuario)
                .token(UUID.randomUUID().toString())
                .expiraEn(Instant.now().plusMillis(refreshExpirationMs))
                .revocado(false)
                .build();

        return tokenRefreshRepository.save(refreshToken);
    }

    public TokenRefresh verifyExpiration(TokenRefresh token) {
        if (token.getExpiraEn().isBefore(Instant.now())) {
            tokenRefreshRepository.delete(token);
            throw new RuntimeException("El token de refresco ha expirado. Inicie sesión nuevamente.");
        }
        if (token.isRevocado()) {
            tokenRefreshRepository.delete(token);
            throw new RuntimeException("El token de refresco ha sido revocado.");
        }
        return token;
    }

    @Transactional
    public void deleteByUsuarioId(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
        if (usuario != null) {
            tokenRefreshRepository.deleteByUsuario(usuario);
        }
    }
}
