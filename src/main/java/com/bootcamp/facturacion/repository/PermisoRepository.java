package com.bootcamp.facturacion.repository;

import com.bootcamp.facturacion.models.auth.Permiso;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PermisoRepository extends JpaRepository<Permiso, Long> {
    Optional<Permiso> findByNombre(String nombre);
}
