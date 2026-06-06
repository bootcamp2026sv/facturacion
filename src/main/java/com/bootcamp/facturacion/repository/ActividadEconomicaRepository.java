package com.bootcamp.facturacion.repository;

import com.bootcamp.facturacion.models.ActividadEconomica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActividadEconomicaRepository extends JpaRepository<ActividadEconomica,Long> {
    Optional<ActividadEconomica> findByCodActividad(String codActividad);
}
