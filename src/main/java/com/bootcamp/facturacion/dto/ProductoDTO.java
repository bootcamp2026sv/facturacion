package com.bootcamp.facturacion.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO para transferencia de datos de producto")
public class ProductoDTO {
    @Schema(description = "ID del producto", example = "1")
    private Long id;

    @Schema(description = "Código del producto", example = "PROD-001")
    private String codigo;

    @Schema(description = "Nombre del producto", example = "Laptop HP")
    private String nombre;

    @Schema(description = "Costo del producto", example = "500.0000")
    private BigDecimal costo;

    @Schema(description = "Precio de venta con IVA", example = "678.0000")
    private BigDecimal precioConIVA;

    @Schema(description = "Precio de venta sin IVA", example = "600.0000")
    private BigDecimal precioSinIVA;

    @Schema(description = "Precio rebajado", example = "0.0000")
    private BigDecimal precioRebajado;

    @Schema(description = "Cantidad en existencia", example = "100.0000")
    private BigDecimal existencia;

    @Schema(description = "Está en consignación", example = "false")
    private boolean consignacion;

    @Schema(description = "Marca del producto", example = "HP")
    private String marca;

    @Schema(description = "Categoría del producto", example = "Electrónica")
    private String categoria;

    @Schema(description = "ID de la categoría", example = "1")
    private Long categoriaId;

    @Schema(description = "Tipo de tributación (GRAVADO, EXENTO, NO_SUJETO, NO_GRAVADO)", example = "GRAVADO")
    private String tipoTributacion;

    @Schema(description = "Descripción del producto", example = "Laptop HP 15.6\"")
    private String descripcion;

    @Schema(description = "Stock mínimo permitido", example = "10.0000")
    private BigDecimal stockMinimo;

    @Schema(description = "Producto activo", example = "true")
    private boolean activo;

    @Schema(description = "ID de la unidad de medida", example = "1")
    private Long unimedidaId;
}
