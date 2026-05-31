package com.bootcamp.facturacion.jsondteDTO.dte05;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class JsonDte05 {
    private Identificacion05 identificacion;
    private List<DocumentoRelacionado05> documentoRelacionado;
    private Emisor05 emisor;
    private Receptor05 receptor;
    private VentaTercero05 ventaTercero;
    private List<CuerpoDocumento05> cuerpoDocumento;
    private Resumen05 resumen;
    private List<Apendice05> apendice;
}
