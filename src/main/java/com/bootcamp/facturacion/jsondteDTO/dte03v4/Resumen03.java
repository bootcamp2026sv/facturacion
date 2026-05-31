package com.bootcamp.facturacion.jsondteDTO.dte03v4;

import com.bootcamp.facturacion.jsondteDTO.PagosV2DTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO sección resumen dte 03 v4")
public class Resumen03 {
    private BigDecimal totalNoSuj;
    private BigDecimal totalExenta;
    private BigDecimal totalGravada;
    private BigDecimal subTotalVentas;
    private BigDecimal descuNoSuj;
    private BigDecimal descuExenta;
    private BigDecimal descuGravada;
    private BigDecimal porcentajeDescuento;
    private BigDecimal totalDescu;
    private List<ResumenTributo03> tributos;
    private BigDecimal subTotal;
    private BigDecimal ivaRete;
    private BigDecimal ivaPerci;
    private BigDecimal montoTotalOperacion;
    private BigDecimal totalNoGravado;
    private BigDecimal totalPagar;
    private String totalLetras;
    private BigDecimal saldoFavor;
    private int condicionOperacion;
    private List<PagosV2DTO> pagos;
    private String numPagoElectronico;
    private String observaciones;
}
