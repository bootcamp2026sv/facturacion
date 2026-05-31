package com.bootcamp.facturacion.jsondteDTO.dte01v2;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO sección CuerpoDocumento dte 01 v2")
public class CuerpoDocumento01 {

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
    private List<String> tributos;
    private BigDecimal psv;
    private BigDecimal noGravado;
    private BigDecimal ivaItem;
}
