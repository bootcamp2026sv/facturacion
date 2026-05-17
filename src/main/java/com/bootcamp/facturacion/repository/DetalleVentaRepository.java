package com.bootcamp.facturacion.repository;


import com.bootcamp.facturacion.models.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta,Long> {}