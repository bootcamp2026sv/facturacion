package com.bootcamp.facturacion.repository;

import com.bootcamp.facturacion.models.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<Venta,Long> {}

