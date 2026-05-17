package com.bootcamp.facturacion.repository;

import com.bootcamp.facturacion.models.Comercio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComercioRepository extends JpaRepository<Comercio,Long> {}