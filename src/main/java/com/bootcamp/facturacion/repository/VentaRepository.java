package com.bootcamp.facturacion.repository;

import com.bootcamp.facturacion.models.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VentaRepository extends JpaRepository<Venta,Long> {
    @Query("SELECT DISTINCT v FROM Venta v LEFT JOIN FETCH v.cliente LEFT JOIN FETCH v.comercio")
    List<Venta> buscarTodasConRelaciones();
}

