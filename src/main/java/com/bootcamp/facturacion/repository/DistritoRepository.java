package com.bootcamp.facturacion.repository;

import com.bootcamp.facturacion.models.Distrito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DistritoRepository extends JpaRepository<Distrito,Long> {
    Optional<Distrito> findByCodigo(String codigo);
}
