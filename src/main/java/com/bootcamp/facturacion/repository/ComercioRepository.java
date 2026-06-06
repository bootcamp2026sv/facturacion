package com.bootcamp.facturacion.repository;

import com.bootcamp.facturacion.models.Comercio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ComercioRepository extends JpaRepository<Comercio,Long> {
    Optional<Comercio> findByNit(String nit);
}