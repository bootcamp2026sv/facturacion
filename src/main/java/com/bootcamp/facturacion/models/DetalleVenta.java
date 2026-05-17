package com.bootcamp.facturacion.models;

import com.bootcamp.facturacion.enums.TipoBien;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "detallesVenta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleVenta{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "numItem", nullable = false)
    public int numItem;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "tipoItem", nullable = false)
    public TipoBien tipoItem; //  enum

    @Column(name = "numeroDocumento", nullable = true)
    public String numeroDocumento;

    @Column(name = "cantidad", nullable = false)
    public float cantidad;

    @Column(name = "codigo", nullable = false)
    public String codigo;

    @Column(name = "codTributo", nullable = true)
    public String codTributo;

    @Column(name = "descripcion", nullable = false)
    public String descripcion;

    @Column(name = "precioUni", nullable = false)
    public double precioUni;

    @Column(name = "montoDescu", nullable = false)
    public double montoDescu;

    @Column(name = "ventaNoSuj", nullable = false)
    public double ventaNoSuj;

    @Column(name = "ventaExenta", nullable = false)
    public double ventaExenta;

    @Column(name = "ventaGravada", nullable = false)
    public double ventaGravada;
    //  public Object tributos; array
    @Column(name = "psv", nullable = false)
    public double psv; //precio sugerido de venta

    @Column(name = "noGravado", nullable = false)
    public double noGravado;

    @Column(name = "ivaItem", nullable = false)
    public double ivaItem;

    //relaciones

    @JsonIgnore //solventar problema de recursion
    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @JsonIgnore //solventar problema de recursion
    @ManyToOne
    @JoinColumn(name = "venta_id", nullable = false)
    private Venta venta;
}
