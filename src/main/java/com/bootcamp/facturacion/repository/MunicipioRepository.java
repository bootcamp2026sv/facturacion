package com.bootcamp.facturacion.repository;

import com.bootcamp.facturacion.models.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio,Long> {
    Optional<Municipio> findByCodigo(String codigo);
}
