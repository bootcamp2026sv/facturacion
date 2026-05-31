package com.bootcamp.facturacion.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "correlativos_dte",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_correlativo_dte",
            columnNames = {"tipo_dte", "ambiente", "anio", "cod_estable", "cod_punto_venta"}
        )
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CorrelativoDte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_dte", nullable = false, length = 2)
    private String tipoDte; // "01", "03", "05", "06"

    @Column(name = "ambiente", nullable = false, length = 2)
    private String ambiente; // "00" = Pruebas, "01" = Producción

    @Column(name = "anio", nullable = false)
    private int anio; // 2026, 2027...

    @Column(name = "cod_estable", nullable = false, length = 4)
    private String codEstable; // "M001" (Matriz) o Sucursal

    @Column(name = "cod_punto_venta", nullable = false, length = 4)
    private String codPuntoVenta; // "P001" (Punto de Venta)

    @Column(name = "ultimo_valor", nullable = false)
    private Long ultimoValor; // Último número asignado
}
