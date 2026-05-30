package com.bootcamp.facturacion.controllers;

import com.bootcamp.facturacion.models.Departamento;
import com.bootcamp.facturacion.services.DepartamentoService;
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
@RequestMapping(value = "api/v1/departamentos")
@Tag(name = "Departamentos", description = "Gestión de departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoService servicio;

    @Operation(summary = "Obtener todos los departamentos",
            description = "Devuelve lista con todos los departamentos registrados")
    @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    @GetMapping
    public List<Departamento> listadoDepartamentos(){
        return servicio.listadoDepartamentos();
    }

    @Operation(summary = "Obtener un departamento",
            description = "Devuelve un departamento registrado por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Departamento obtenido exitosamente"),
            @ApiResponse(responseCode = "404", description = "Departamento no encontrado")
    })
    @GetMapping("/{id}")
    public Departamento unDepartamento(
            @Parameter(description = "ID del departamento", example = "1")
            @PathVariable Long id
    ){
        return servicio.unDepartamento(id);
    }

    @Operation(summary = "Crear un departamento",
            description = "Registra un nuevo departamento en el sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Departamento creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Departamento guardar(@RequestBody Departamento depto){
        return servicio.guardar(depto);
    }

    @Operation(summary = "Eliminar un departamento",
            description = "Elimina un departamento registrado por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Departamento eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Departamento no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID del departamento", example = "1")
            @PathVariable Long id
    ){
       servicio.eliminarDepartamento(id);
       return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Actualizar un departamento",
            description = "Actualiza los datos de un departamento existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Departamento actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Departamento no encontrado")
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Departamento actualizar(
            @Parameter(description = "ID del departamento", example = "1")
            @PathVariable Long id,
            @RequestBody Departamento depto
    ) {
        depto.setId(id);
        return servicio.actualizar(depto);
    }

}
