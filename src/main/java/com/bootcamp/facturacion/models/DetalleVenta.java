package com.bootcamp.facturacion.models;

import com.bootcamp.facturacion.enums.TipoBien;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "detallesVenta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Detalle de línea de una venta")
public class DetalleVenta{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "ID autogenerado", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long Id;

    @Column(name = "numItem", nullable = false)
    @Schema(description = "Número de ítem en la factura", example = "1")
    public int numItem;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "tipoItem", nullable = false)
    @Schema(description = "Tipo de bien: 0 = BIEN, 1 = SERVICIO, 2 = AMBOS, 3 = OTROS")
    public TipoBien tipoItem;

    @Column(name = "numeroDocumento", nullable = true)
    @Schema(description = "Número de documento", example = "null")
    public String numeroDocumento;

    @Column(name = "cantidad", nullable = false, precision = 18, scale = 4)
    @Schema(description = "Cantidad del producto", example = "1.0000")
    public BigDecimal cantidad;

    @Column(name = "codigo", nullable = false)
    @Schema(description = "Código del producto", example = "PROD-001")
    public String codigo;

    @Column(name = "codTributo", nullable = true)
    @Schema(description = "Código de tributo", example = "null")
    public String codTributo;

    @Column(name = "descripcion", nullable = false)
    @Schema(description = "Descripción del producto", example = "Laptop HP 15.6\"")
    public String descripcion;

    @Column(name = "precioUni", nullable = false, precision = 18, scale = 4)
    @Schema(description = "Precio unitario", example = "600.0000")
    public BigDecimal precioUni;

    @Column(name = "montoDescu", nullable = false, precision = 18, scale = 4)
    @Schema(description = "Monto de descuento", example = "0.0000")
    public BigDecimal montoDescu;

    @Column(name = "ventaNoSuj", nullable = false, precision = 18, scale = 4)
    @Schema(description = "Venta no sujeta", example = "0.0000")
    public BigDecimal ventaNoSuj;

    @Column(name = "ventaExenta", nullable = false, precision = 18, scale = 4)
    @Schema(description = "Venta exenta", example = "0.0000")
    public BigDecimal ventaExenta;

    @Column(name = "ventaGravada", nullable = false, precision = 18, scale = 4)
    @Schema(description = "Venta gravada", example = "600.0000")
    public BigDecimal ventaGravada;

    @Column(name = "psv", nullable = false, precision = 18, scale = 4)
    @Schema(description = "Precio sugerido de venta", example = "600.0000")
    public BigDecimal psv;

    @Column(name = "noGravado", nullable = false, precision = 18, scale = 4)
    @Schema(description = "No gravado", example = "0.0000")
    public BigDecimal noGravado;

    @Column(name = "ivaItem", nullable = false, precision = 18, scale = 4)
    @Schema(description = "IVA del ítem", example = "78.0000")
    public BigDecimal ivaItem;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    @Schema(description = "Producto asociado")
    private Producto producto;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "venta_id", nullable = false)
    @Schema(description = "Venta a la que pertenece")
    private Venta venta;
}
