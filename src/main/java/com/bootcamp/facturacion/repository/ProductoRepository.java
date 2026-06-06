package com.bootcamp.facturacion.repository;

import com.bootcamp.facturacion.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto,Long> {
    Optional<Producto> findByCodigo(String codigo);
}