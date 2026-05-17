package com.bootcamp.facturacion.repository;

import com.bootcamp.facturacion.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {}