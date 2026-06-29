package com.bootcamp.facturacion.controllers;

import com.bootcamp.facturacion.models.UnidadDeMedida;
import com.bootcamp.facturacion.services.UnidadDeMedidaService;
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
@RequestMapping(value = "api/v1/UnidadDeMedidas")
@Tag(name = "Unidades de Medida", description = "Gestión de unidades de medida")
// @CrossOrigin(origins = "*")
public class UnidadDeMedidaController {

    @Autowired
    private UnidadDeMedidaService servicio;

    @Operation(summary = "Obtener todas las unidades de medida",
            description = "Devuelve lista con todas las unidades de medida registradas")
    @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    @GetMapping
    public List<UnidadDeMedida> listadoUnidadDeMedidas(){
        return servicio.listadoUnidadDeMedida();
    }

    @Operation(summary = "Obtener una unidad de medida",
            description = "Devuelve una unidad de medida registrada por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Unidad de medida obtenida exitosamente"),
            @ApiResponse(responseCode = "404", description = "Unidad de medida no encontrada")
    })
    @GetMapping("/{id}")
    public UnidadDeMedida unUnidadDeMedida(
            @Parameter(description = "ID de la unidad de medida", example = "1")
            @PathVariable Long id
    ){
        return servicio.unaUnidadDeMedida(id);
    }

    @Operation(summary = "Crear una unidad de medida",
            description = "Registra una nueva unidad de medida en el sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Unidad de medida creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UnidadDeMedida guardar(@RequestBody UnidadDeMedida unidadDeMedida){
        return servicio.guardar(unidadDeMedida);
    }

    @Operation(summary = "Eliminar una unidad de medida",
            description = "Elimina una unidad de medida registrada por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Unidad de medida eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Unidad de medida no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID de la unidad de medida", example = "1")
            @PathVariable Long id
    ){
       servicio.eliminarUnidadDeMedida(id);
       return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Actualizar una unidad de medida",
            description = "Actualiza los datos de una unidad de medida existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Unidad de medida actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Unidad de medida no encontrada")
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UnidadDeMedida actualizar(
            @Parameter(description = "ID de la unidad de medida", example = "1")
            @PathVariable Long id,
            @RequestBody UnidadDeMedida unidadDeMedida
    ) {
        unidadDeMedida.setId(id);
        return servicio.actualizar(unidadDeMedida);
    }

}
