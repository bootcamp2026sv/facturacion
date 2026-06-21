package com.bootcamp.facturacion.controllers;

import com.bootcamp.facturacion.models.Categoria;
import com.bootcamp.facturacion.services.CategoriaService;
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
@RequestMapping(value = "api/v1/Categorias")
@Tag(name = "Categorías", description = "Gestión de categorías de productos")
public class CategoriaController {

    @Autowired
    private CategoriaService servicio;

    @Operation(summary = "Obtener todas las categorías",
            description = "Devuelve lista con todas las categorías registradas")
    @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    @GetMapping
    public List<Categoria> listadoCategorias() {
        return servicio.listadoCategorias();
    }

    @Operation(summary = "Obtener una categoría por ID",
            description = "Devuelve una categoría registrada por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Categoría obtenida exitosamente"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    @GetMapping("/{id}")
    public Categoria unaCategoria(
            @Parameter(description = "ID de la categoría", example = "1")
            @PathVariable Long id
    ) {
        return servicio.unaCategoria(id);
    }

    @Operation(summary = "Crear una categoría",
            description = "Registra una nueva categoría en el sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Categoría creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Categoria guardar(@RequestBody Categoria categoria) {
        return servicio.guardar(categoria);
    }

    @Operation(summary = "Actualizar una categoría",
            description = "Actualiza los datos de una categoría existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Categoría actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Categoria actualizar(
            @Parameter(description = "ID de la categoría", example = "1")
            @PathVariable Long id,
            @RequestBody Categoria categoria
    ) {
        categoria.setId(id);
        return servicio.actualizar(categoria);
    }

    @Operation(summary = "Eliminar una categoría",
            description = "Elimina una categoría registrada por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Categoría eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID de la categoría", example = "1")
            @PathVariable Long id
    ) {
        servicio.eliminarCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
