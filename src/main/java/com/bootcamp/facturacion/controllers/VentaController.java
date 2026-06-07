package com.bootcamp.facturacion.controllers;

import com.bootcamp.facturacion.models.Venta;
import com.bootcamp.facturacion.services.VentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/Ventas")
@Tag(name = "Ventas", description = "Gestión de ventas")
public class VentaController {

    @Autowired
    private VentaService servicio;

    @Operation(summary = "Obtener todas las ventas",
            description = "Devuelve lista con todas las ventas registradas")
    @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    @GetMapping
    public List<Venta> listadoVentas(){
        return servicio.listadoVentas();
    }

    @Operation(summary = "Obtener una venta",
            description = "Devuelve una venta registrada por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Venta obtenida exitosamente"),
            @ApiResponse(responseCode = "404", description = "Venta no encontrada")
    })
    @GetMapping("/{id}")
    public Venta unVenta(
            @Parameter(description = "ID de la venta", example = "1")
            @PathVariable Long id
    ){
        return servicio.unaVenta(id);
    }

    @Operation(summary = "Crear una venta",
            description = "Registra una nueva venta en el sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Venta creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Venta guardar(
            @RequestParam(name = "validar", required = false, defaultValue = "true") boolean validar,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "JSON de la venta a registrar",
                    required = true,
                    content = @io.swagger.v3.oas.annotations.media.Content(
                            mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(
                                    type = "object",
                                    example = """
                                    {
                                      "version": 1,
                                      "ambiente": "00",
                                      "tipoDte": "01",
                                      "numeroControl": "DTE-01-M001P001-000000000001000",
                                      "codigoGeneracion": "288e60c6-aeb4-414b-9227-9b4c16d35c1e",
                                      "tipoModelo": 1,
                                      "tipoOperacion": 1,
                                      "fecha": "2025-01-15T10:30:00",
                                      "tipoMoneda": "USD",
                                      "jsonVenta": "",
                                      "totalGeneral": "100.0000",
                                      "totalExento": "0.0000",
                                      "totalNoSujeto": "0.0000",
                                      "totalGravado": "100.0000",
                                      "totalNoGravado": "0.0000",
                                      "totalDescuento": "0.0000",
                                      "totalIva": "13.0000",
                                      "cliente": {
                                        "id": 6
                                      },
                                      "comercio": {
                                        "id": 1
                                      },
                                      "detallesVenta": [
                                        {
                                          "numItem": 1,
                                          "tipoItem": "BIEN",
                                          "cantidad": "1.0000",
                                          "codigo": "PROD-001",
                                          "descripcion": "Laptop HP 15.6\\\"",
                                          "precioUni": "600.0000",
                                          "montoDescu": "0.0000",
                                          "ventaNoSuj": "0.0000",
                                          "ventaExenta": "0.0000",
                                          "ventaGravada": "600.0000",
                                          "psv": "600.0000",
                                          "noGravado": "0.0000",
                                          "ivaItem": "78.0000",
                                          "producto": {
                                            "id": 2
                                          }
                                        }
                                      ]
                                    }
                                    """
                            )
                    )
            )
            @RequestBody Venta venta
    ) {
        System.out.println(">>> POST /api/v1/Ventas llamado con validar = " + validar + ", numeroControl en JSON: " + venta.getNumeroControl());
        return servicio.guardar(venta, validar);
    }

    @Operation(summary = "Actualizar una venta",
            description = "Actualiza los datos de una venta existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Venta actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Venta no encontrada")
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Venta actualizar(
            @Parameter(description = "ID de la venta", example = "1")
            @PathVariable Long id,
            @RequestBody Venta venta
    ) {
        venta.setId(id);
        return servicio.actualizar(venta);
    }

}
