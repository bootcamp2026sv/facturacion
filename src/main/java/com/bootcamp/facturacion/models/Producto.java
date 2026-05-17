package com.bootcamp.facturacion.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "productos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "codigo", nullable = false)
    private String codigo;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "costo", nullable = false, precision = 18, scale = 4)
    private BigDecimal costo;

    @Column(name = "precioConIVA", nullable = false, precision = 18, scale = 4)
    private BigDecimal precioConIVA;

    @Column(name = "precioSinIVA", nullable = false, precision = 18, scale = 4)
    private BigDecimal precioSinIVA;

    @Column(name = "precioRebajado", nullable = false, precision = 18, scale = 4)
    private BigDecimal precioRebajado;

    @Column(name = "existencia", nullable = false, precision = 18, scale = 4)
    private BigDecimal existencia;

    @Column(name = "consignacion", nullable = false)
    private boolean consignacion;

    @Column(name = "marca", nullable = false)
    private String marca;

    @Column(name = "categoria", nullable = false)
    private String categoria;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "stockMinimo", nullable = false)
    private BigDecimal stockMinimo;

    @Column(name = "activo", nullable = false)
    private boolean activo; // soft delete

    //relaciones
    @JsonIgnore //solventar problema de recursion
    @ManyToOne
    @JoinColumn(name = "unimedida_id", nullable = false)
    private UnidadDeMedida uniMedida;
}

// despues No Gravado, No Sujeto, Exento
