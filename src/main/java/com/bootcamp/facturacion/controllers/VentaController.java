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
import org.springframework.http.ResponseEntity;
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
    public Venta guardar(@RequestBody Venta venta){
        return servicio.guardar(venta);
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
