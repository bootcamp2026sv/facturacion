package com.bootcamp.facturacion.jsondteDTO.dte01v2;

import com.bootcamp.facturacion.models.Venta;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class JsonDte01V2 {
    private Identificacion01 identificacion;
    private List<DocumentoRelacionado01> documentoRelacionado;
    private Emisor01 emisor;
    private Receptor01 receptor;
    private VentaTercero01 ventaTercero;
    private List<CuerpoDocumento01> cuerpoDocumento;
    private Resumen01 resumen;
    private List<OtrosDocumentos01> otrosDocumentos;
    private List<Apendice01> apendice;
}


// DTO= clase en java
// DTE= Documento Tributario Electronico
// DTE = distincion de que esa clase forma parte del json a enviar