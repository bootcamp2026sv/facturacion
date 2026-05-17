package com.bootcamp.facturacion.repository;

import com.bootcamp.facturacion.models.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VentaRepository extends JpaRepository<Venta,Long> {}

