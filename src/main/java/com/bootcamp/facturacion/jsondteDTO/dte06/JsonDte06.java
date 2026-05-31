package com.bootcamp.facturacion.jsondteDTO.dte06;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class JsonDte06 {
    private Identificacion06 identificacion;
    private List<DocumentoRelacionado06> documentoRelacionado;
    private Emisor06 emisor;
    private Receptor06 receptor;
    private VentaTercero06 ventaTercero;
    private List<CuerpoDocumento06> cuerpoDocumento;
    private Resumen06 resumen;
    private List<Apendice06> apendice;
}
