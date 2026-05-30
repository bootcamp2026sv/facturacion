package com.bootcamp.facturacion.controllers;

import com.bootcamp.facturacion.dto.ComercioDTO;
import com.bootcamp.facturacion.models.Comercio;
import com.bootcamp.facturacion.services.ComercioService;
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
@RequestMapping(value = "api/v1/Comercios")
@Tag(name = "Comercios", description = "Gestión de comercios")
public class ComercioController {

    @Autowired
    private ComercioService servicio;

    @Operation(summary = "Obtener todos los comercios",
            description = "Devuelve lista con todos los comercios registrados")
    @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    @GetMapping
    public List<Comercio> listadoComercios(){
        return servicio.listadoComercios();
    }

    @Operation(summary = "Obtener un comercio",
            description = "Devuelve un comercio registrado por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Comercio obtenido exitosamente"),
            @ApiResponse(responseCode = "404", description = "Comercio no encontrado")
    })
    @GetMapping("/{id}")
    public Comercio unComercio(
            @Parameter(description = "ID del comercio", example = "1")
            @PathVariable Long id
    ){
        return servicio.unComercio(id);
    }

    @Operation(summary = "Crear un comercio",
            description = "Registra un nuevo comercio en el sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Comercio creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Comercio guardar(@RequestBody ComercioDTO comercio){
        return servicio.guardar(comercio);
    }

    @Operation(summary = "Eliminar un comercio",
            description = "Elimina un comercio registrado por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Comercio eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Comercio no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID del comercio", example = "1")
            @PathVariable Long id
    ){
       servicio.eliminarComercio(id);
       return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Actualizar un comercio",
            description = "Actualiza los datos de un comercio existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Comercio actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Comercio no encontrado")
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Comercio actualizar(
            @Parameter(description = "ID del comercio", example = "1")
            @PathVariable Long id,
            @RequestBody ComercioDTO comercio
    ) {
        comercio.setId(id);
        return servicio.actualizar(comercio);
    }

}
