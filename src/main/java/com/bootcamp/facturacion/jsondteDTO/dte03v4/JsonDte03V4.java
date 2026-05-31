package com.bootcamp.facturacion.jsondteDTO.dte03v4;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class JsonDte03V4 {
    private Identificacion03 identificacion;
    private List<DocumentoRelacionado03> documentoRelacionado;
    private Emisor03 emisor;
    private Receptor03 receptor;
    private VentaTercero03 ventaTercero;
    private List<CuerpoDocumento03> cuerpoDocumento;
    private Resumen03 resumen;
    private List<OtrosDocumentos03> otrosDocumentos;
    private List<Apendice03> apendice;
}
