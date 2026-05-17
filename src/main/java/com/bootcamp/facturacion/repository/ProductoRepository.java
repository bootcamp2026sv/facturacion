package com.bootcamp.facturacion.repository;

import com.bootcamp.facturacion.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto,Long> {}