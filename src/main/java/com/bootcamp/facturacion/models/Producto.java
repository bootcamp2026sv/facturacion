package com.bootcamp.facturacion.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Entidad que representa un producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID autogenerado", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long Id;

    @Column(name = "codigo", nullable = false)
    @Schema(description = "Código del producto", example = "PROD-001")
    private String codigo;

    @Column(name = "nombre", nullable = false)
    @Schema(description = "Nombre del producto", example = "Laptop HP")
    private String nombre;

    @Column(name = "costo", nullable = false, precision = 18, scale = 4)
    @Schema(description = "Costo del producto", example = "500.0000")
    private BigDecimal costo;

    @Column(name = "precioConIVA", nullable = false, precision = 18, scale = 4)
    @Schema(description = "Precio de venta con IVA", example = "680.0000")
    private BigDecimal precioConIVA;

    @Column(name = "precioSinIVA", nullable = false, precision = 18, scale = 4)
    @Schema(description = "Precio de venta sin IVA", example = "600.0000")
    private BigDecimal precioSinIVA;

    @Column(name = "precioRebajado", nullable = false, precision = 18, scale = 4)
    @Schema(description = "Precio rebajado", example = "0.0000")
    private BigDecimal precioRebajado;

    @Column(name = "existencia", nullable = false, precision = 18, scale = 4)
    @Schema(description = "Cantidad en existencia", example = "100.0000")
    private BigDecimal existencia;

    @Column(name = "consignacion", nullable = false)
    @Schema(description = "Está en consignación", example = "false")
    private boolean consignacion;

    @Column(name = "marca", nullable = false)
    @Schema(description = "Marca del producto", example = "HP")
    private String marca;

    @Column(name = "categoria", nullable = false)
    @Schema(description = "Categoría del producto", example = "Electrónica")
    private String categoria;

    @Column(name = "descripcion", nullable = false)
    @Schema(description = "Descripción del producto", example = "Laptop HP 15.6\"")
    private String descripcion;

    @Column(name = "stockMinimo", nullable = false)
    @Schema(description = "Stock mínimo permitido", example = "10.0000")
    private BigDecimal stockMinimo;

    @Column(name = "activo", nullable = false)
    @Schema(description = "Producto activo (soft delete)", example = "true")
    private boolean activo;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "unimedida_id", nullable = false)
    @Schema(description = "Unidad de medida del producto")
    private UnidadDeMedida uniMedida;
}
