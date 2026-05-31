package com.bootcamp.facturacion.jsondteDTO.dte06;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO sección resumen dte 06")
public class Resumen06 {
    private BigDecimal totalNoSuj;
    private BigDecimal totalExenta;
    private BigDecimal totalGravada;
    private BigDecimal subTotalVentas;
    private BigDecimal totalDescu;
    private BigDecimal totalPagar;
    private BigDecimal totalNoGravado;
    private String observaciones;
    private BigDecimal totalIva;
    private List<ResumenTributo06> tributos;
    private BigDecimal ivaPerci;
    private BigDecimal ivaRete;
    private String codigoRetencionMH;
    private BigDecimal montoTotalOperacion;
    private String totalLetras;
    private int condicionOperacion;
    private String numPagoElectronico;
}
