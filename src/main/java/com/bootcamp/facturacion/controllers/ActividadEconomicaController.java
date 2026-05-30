package com.bootcamp.facturacion.controllers;

import com.bootcamp.facturacion.models.ActividadEconomica;
import com.bootcamp.facturacion.services.ActividadEconomicaService;
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
@RequestMapping(value = "api/v1/ActividadEconomicas")
@Tag(name = "Actividades Económicas", description = "Gestión de actividades económicas")
public class ActividadEconomicaController {

    @Autowired
    private ActividadEconomicaService servicio;

    @Operation(summary = "Obtener todas las actividades económicas",
            description = "Devuelve lista con todas las actividades económicas registradas")
    @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    @GetMapping
    public List<ActividadEconomica> listadoActividadEconomicas(){
        return servicio.listadoActividades();
    }

    @Operation(summary = "Obtener una actividad económica",
            description = "Devuelve una actividad económica registrada por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Actividad económica obtenida exitosamente"),
            @ApiResponse(responseCode = "404", description = "Actividad económica no encontrada")
    })
    @GetMapping("/{id}")
    public ActividadEconomica unActividadEconomica(
            @Parameter(description = "ID de la actividad económica", example = "1")
            @PathVariable Long id
    ){
        return servicio.unActividad(id);
    }

    @Operation(summary = "Crear una actividad económica",
            description = "Registra una nueva actividad económica en el sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Actividad económica creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ActividadEconomica guardar(@RequestBody ActividadEconomica actividadEconomica){
        return servicio.guardar(actividadEconomica);
    }

    @Operation(summary = "Eliminar una actividad económica",
            description = "Elimina una actividad económica registrada por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Actividad económica eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Actividad económica no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID de la actividad económica", example = "1")
            @PathVariable Long id
    ){
       servicio.eliminarActividad(id);
       return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Actualizar una actividad económica",
            description = "Actualiza los datos de una actividad económica existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Actividad económica actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Actividad económica no encontrada")
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ActividadEconomica actualizar(
            @Parameter(description = "ID de la actividad económica", example = "1")
            @PathVariable Long id,
            @RequestBody ActividadEconomica actividadEconomica
    ) {
        actividadEconomica.setId(id);
        return servicio.actualizar(actividadEconomica);
    }

}
