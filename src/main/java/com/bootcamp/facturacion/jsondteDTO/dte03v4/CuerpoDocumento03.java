package com.bootcamp.facturacion.jsondteDTO.dte03v4;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO sección CuerpoDocumento dte 03 v4")
public class CuerpoDocumento03 {
    private int numItem;
    private int tipoItem;
    private String numeroDocumento;
    private BigDecimal cantidad;
    private String codigo;
    private String codTributo;
    private int uniMedida;
    private String descripcion;
    private BigDecimal precioUni;
    private BigDecimal montoDescu;
    private BigDecimal ventaNoSuj;
    private BigDecimal ventaExenta;
    private BigDecimal ventaGravada;
    private BigDecimal psv;
    private BigDecimal noGravado;
    private List<String> tributos;
}
