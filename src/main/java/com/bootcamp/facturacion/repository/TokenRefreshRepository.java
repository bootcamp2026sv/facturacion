package com.bootcamp.facturacion.repository;

import com.bootcamp.facturacion.models.auth.TokenRefresh;
import com.bootcamp.facturacion.models.auth.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TokenRefreshRepository extends JpaRepository<TokenRefresh, Long> {
    Optional<TokenRefresh> findByToken(String token);
    Optional<TokenRefresh> findByUsuario(Usuario usuario);
    void deleteByUsuario(Usuario usuario);
}
