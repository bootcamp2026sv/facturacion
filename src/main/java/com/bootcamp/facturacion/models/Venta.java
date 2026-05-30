package com.bootcamp.facturacion.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ventas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Entidad que representa una venta (DTE)")
public class Venta{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID autogenerado", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long Id;

    @Column(name = "version", nullable = false)
    @Schema(description = "Versión del DTE", example = "1")
    private int version;

    @Column(name = "ambiente", nullable = false)
    @Schema(description = "Ambiente: 00 = prueba, 01 = producción", example = "00")
    private String ambiente;

    @Column(name = "tipoDte", nullable = false)
    @Schema(description = "Tipo de DTE: 01 = consumidor final, 03 = crédito fiscal", example = "01")
    private String tipoDte;

    @Column(name = "numeroControl", nullable = false)
    @Schema(description = "Número de control del DTE", example = "DTE-01-M001P001-000000000001000")
    private String numeroControl;

    @Column(name = "codigoGeneracion", nullable = false)
    @Schema(description = "Código de generación (UUID v4)", example = "288e60c6-aeb4-414b-9227-9b4c16d35c1e")
    private String codigoGeneracion;

    @Column(name = "tipoModelo", nullable = false)
    @Schema(description = "Tipo de modelo", example = "1")
    private int tipoModelo;

    @Column(name = "tipoOperacion", nullable = false)
    @Schema(description = "Tipo de operación", example = "1")
    private int tipoOperacion;

    @Column(name = "tipoContingencia", nullable = true)
    @Schema(description = "Tipo de contingencia", example = "null")
    private String tipoContingencia;

    @Column(name = "motivoContin", nullable = true)
    @Schema(description = "Motivo de contingencia", example = "null")
    private String motivoContin;

    @Column(name = "fecha", nullable = false)
    @Schema(description = "Fecha y hora de la venta", example = "2025-01-15T10:30:00")
    private LocalDateTime fecha;

    @Column(name = "tipoMoneda", nullable = false)
    @Schema(description = "Tipo de moneda", example = "USD")
    private String tipoMoneda;

    @Column(name = "jsonVenta", nullable = false)
    @Schema(description = "JSON de la venta enviado a Hacienda")
    private String jsonVenta;

    @Column(name = "selloRecepcion", nullable = true)
    @Schema(description = "Sello de recepción de Hacienda")
    private String selloRecepcion;

    @Column(name = "jsonAnulacion", nullable = true)
    @Schema(description = "JSON de anulación enviado a Hacienda")
    private String jsonAnulacion;

    @Column(name = "selloAnulacion", nullable = true)
    @Schema(description = "Sello de anulación de Hacienda")
    private String selloAnulacion;

    @Column(name = "totalGeneral", nullable = false, precision = 18, scale = 4)
    @Schema(description = "Total general de la venta", example = "100.0000")
    private BigDecimal totalGeneral;

    @Column(name = "totalExento", nullable = false, precision = 18, scale = 4)
    @Schema(description = "Total exento", example = "0.0000")
    private BigDecimal totalExento;

    @Column(name = "totalNoSujeto", nullable = false, precision = 18, scale = 4)
    @Schema(description = "Total no sujeto", example = "0.0000")
    private BigDecimal totalNoSujeto;

    @Column(name = "totalGravado", nullable = false, precision = 18, scale = 4)
    @Schema(description = "Total gravado", example = "100.0000")
    private BigDecimal totalGravado;

    @Column(name = "totalNoGravado", nullable = false, precision = 18, scale = 4)
    @Schema(description = "Total no gravado", example = "0.0000")
    private BigDecimal totalNoGravado;

    @Column(name = "totalDescuento", nullable = false, precision = 18, scale = 4)
    @Schema(description = "Total descuento", example = "0.0000")
    private BigDecimal totalDescuento;

    @Column(name = "totalIva", nullable = false, precision = 18, scale = 4)
    @Schema(description = "Total IVA", example = "13.0000")
    private BigDecimal totalIva;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = false)
    @Schema(description = "Detalles de la venta (líneas de productos)")
    private List<DetalleVenta> detallesVenta = new ArrayList<>();

}
