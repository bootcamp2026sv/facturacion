package com.bootcamp.facturacion.controllers;

import com.bootcamp.facturacion.services.CorrelativoDteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Controlador REST para gestionar la generación y consulta de correlativos de DTE
 * para el Ministerio de Hacienda (MH).
 * <p>
 * Nota: Se ubica bajo el prefijo "/auth" para permitir el acceso público de pruebas
 * sin requerir autenticación JWT.
 * </p>
 */
@RestController
@RequestMapping("api/v1/auth/correlativos")
@Tag(name = "Correlativos DTE", description = "Endpoints para la gestión y generación de números de control correlativos para DTE")
public class CorrelativoDteController {

    @Autowired
    private CorrelativoDteService correlativoDteService;

    /**
     * Genera e incrementa el número de control correlativo siguiente según los parámetros de DTE.
     *
     * @param tipoDte       Tipo de Documento Tributario Electrónico (ej: "01", "03").
     * @param ambiente      Ambiente de facturación ("00" para pruebas, "01" para producción).
     * @param codEstable    Código del establecimiento de 4 caracteres.
     * @param codPuntoVenta Código del punto de venta de 4 caracteres.
     * @return Mapa con la estructura de la respuesta conteniendo el número de control generado.
     */
    @Operation(
            summary = "Generar siguiente número de control DTE",
            description = "Obtiene e incrementa el correlativo correspondiente en la base de datos, retornando el número de control estructurado y formateado a 15 dígitos."
    )
    @ApiResponse(responseCode = "200", description = "Número de control generado exitosamente")
    @GetMapping("/siguiente")
    public Map<String, Object> obtenerSiguienteNumeroControl(
            @Parameter(description = "Tipo de DTE (ej. 01 para Factura, 03 para CCF)", example = "01")
            @RequestParam(defaultValue = "01") String tipoDte,

            @Parameter(description = "Ambiente de destino (00 = Pruebas, 01 = Producción)", example = "00")
            @RequestParam(defaultValue = "00") String ambiente,

            @Parameter(description = "Código del establecimiento (4 caracteres)", example = "M001")
            @RequestParam(defaultValue = "M001") String codEstable,

            @Parameter(description = "Código del punto de venta (4 caracteres)", example = "P001")
            @RequestParam(defaultValue = "P001") String codPuntoVenta
    ) {
        String numeroControl = correlativoDteService.obtenerSiguienteNumeroControl(tipoDte, ambiente, codEstable, codPuntoVenta);

        Map<String, Object> response = new HashMap<>();
        response.put("tipoDte", tipoDte);
        response.put("ambiente", ambiente);
        response.put("codEstable", codEstable);
        response.put("codPuntoVenta", codPuntoVenta);
        response.put("numeroControl", numeroControl);

        return response;
    }
}
