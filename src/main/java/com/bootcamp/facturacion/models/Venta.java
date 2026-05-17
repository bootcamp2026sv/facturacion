package com.bootcamp.facturacion.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
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
    @Column(name = "totalGeneral", nullable = false)
    private double totalGeneral;

    @Column(name = "totalExento", nullable = false)
    private double totalExento;

    @Column(name = "totalNoSujeto", nullable = false)
    private double totalNoSujeto;

    @Column(name = "totalGravado", nullable = false)
    private double totalGravado;

    @Column(name = "totalNoGravado", nullable = false)
    private double totalNoGravado;

    @Column(name = "totalDescuento", nullable = false)
    private double totalDescuento;

    @Column(name = "totalIva", nullable = false)
    private double totalIva;

}

//M001 = codigo del establecimiento
//P001= codigo punto de venta
