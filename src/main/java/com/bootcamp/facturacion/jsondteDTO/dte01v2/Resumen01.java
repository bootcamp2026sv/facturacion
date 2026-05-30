package com.bootcamp.facturacion.jsondteDTO.dte01v2;

import com.bootcamp.facturacion.jsondteDTO.PagosV2DTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO sección interna direccion dte v2")
public class Resumen01 {
    private BigDecimal totalNoSuj;
    private BigDecimal totalExenta;
    private BigDecimal totalGravada;
    private BigDecimal totalNoGravado;
    private BigDecimal subTotalVentas;
    private BigDecimal descuNoSuj;
    private BigDecimal descuExenta;
    private BigDecimal descuGravada;
    private BigDecimal porcentajeDescuento;
    private BigDecimal totalDescu;
    private String tributos;
    private BigDecimal subTotal;
    private BigDecimal ivaRete;
    private BigDecimal montoTotalOperacion;
    private BigDecimal totalPagar;
    private String totalLetras;
    private BigDecimal totalIva;
    private BigDecimal saldoFavor;
    private int condicionOperacion;
    private ArrayList<PagosV2DTO> pagos;
    private String numPagoElectronico;
    private String observaciones;
}
