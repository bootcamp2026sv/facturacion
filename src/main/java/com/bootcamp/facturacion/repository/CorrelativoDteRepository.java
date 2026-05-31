package com.bootcamp.facturacion.repository;

import com.bootcamp.facturacion.models.CorrelativoDte;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CorrelativoDteRepository extends JpaRepository<CorrelativoDte, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT c FROM CorrelativoDte c WHERE c.tipoDte = :tipoDte AND c.ambiente = :ambiente " +
           "AND c.anio = :anio AND c.codEstable = :codEstable AND c.codPuntoVenta = :codPuntoVenta")
    Optional<CorrelativoDte> obtenerCorrelativoConBloqueo(
        @Param("tipoDte") String tipoDte,
        @Param("ambiente") String ambiente,
        @Param("anio") int anio,
        @Param("codEstable") String codEstable,
        @Param("codPuntoVenta") String codPuntoVenta
    );
}
