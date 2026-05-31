package com.bootcamp.facturacion.jsondteDTO.anulacion;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JsonAnulacion {
    private IdentificacionAnulacion identificacion;
    private EmisorAnulacion emisor;
    private DocumentoAnulacion documento;
    private MotivoAnulacion motivo;
}
