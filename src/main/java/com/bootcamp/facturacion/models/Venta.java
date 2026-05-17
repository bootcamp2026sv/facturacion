package com.bootcamp.facturacion.models;

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
public class Venta{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "version", nullable = false)
    private int version;

    @Column(name = "ambiente", nullable = false)
    private String ambiente;// 00 prueba 01 produccion

    @Column(name = "tipoDte", nullable = false)
    private String tipoDte;// 01 consumidor 03 credito fiscal

    @Column(name = "numeroControl", nullable = false)
    private String numeroControl; // se compone DTE-01-M001P001-000000000001000

    @Column(name = "codigoGeneracion", nullable = false)
    private String codigoGeneracion; // UUID v4 ejemplo 288e60c6-aeb4-414b-9227-9b4c16d35c1e

    @Column(name = "tipoModelo", nullable = false)
    private int tipoModelo;// 1

    @Column(name = "tipoOperacion", nullable = false)
    private int tipoOperacion; //1

    @Column(name = "tipoContingencia", nullable = true)
    private String tipoContingencia; //null

    @Column(name = "motivoContin", nullable = true)
    private String motivoContin; //null

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    @Column(name = "tipoMoneda", nullable = false)
    private String tipoMoneda;//USD

    //valores presentados a hacienda
    @Column(name = "jsonVenta", nullable = false)
    private String jsonVenta;

    @Column(name = "selloRecepcion", nullable = true)
    private String selloRecepcion;

    @Column(name = "jsonAnulacion", nullable = true)
    private String jsonAnulacion;

    @Column(name = "selloAnulacion", nullable = true)
    private String selloAnulacion;

    //TOTAL
    @Column(name = "totalGeneral", nullable = false, precision = 18, scale = 4)
    private BigDecimal totalGeneral;

    @Column(name = "totalExento", nullable = false, precision = 18, scale = 4)
    private BigDecimal totalExento;

    @Column(name = "totalNoSujeto", nullable = false, precision = 18, scale = 4)
    private BigDecimal totalNoSujeto;

    @Column(name = "totalGravado", nullable = false, precision = 18, scale = 4)
    private BigDecimal totalGravado;

    @Column(name = "totalNoGravado", nullable = false, precision = 18, scale = 4)
    private BigDecimal totalNoGravado;

    @Column(name = "totalDescuento", nullable = false, precision = 18, scale = 4)
    private BigDecimal totalDescuento;

    @Column(name = "totalIva", nullable = false, precision = 18, scale = 4)
    private BigDecimal totalIva;


    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<DetalleVenta> detallesVenta = new ArrayList<>();

}

//M001 = codigo del establecimiento
//P001= codigo punto de venta
