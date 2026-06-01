package com.bootcamp.facturacion.controllers;

import com.bootcamp.facturacion.models.Producto;
import com.bootcamp.facturacion.services.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/Productos")
@Tag(name = "Productos", description = "Gestión de productos")

public class ProductoController {

    @Autowired
    private ProductoService servicio;

    @Operation(summary = "Obtener todos los productos",
            description = "Devuelve lista con todos los productos registrados")
    @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    @GetMapping

    public List<Producto> listadoProductos(){
        return servicio.listadoProductos();
    }

    @Operation(summary = "Obtener un producto",
            description = "Devuelve un producto registrado por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto obtenido exitosamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @GetMapping("/{id}")
    public Producto unProducto(
            @Parameter(description = "ID del producto", example = "1")
            @PathVariable Long id
    ){
        return servicio.unProducto(id);
    }

    @Operation(summary = "Crear un producto",
            description = "Registra un nuevo producto en el sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Producto creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Producto guardar(@RequestBody Producto producto){
        return servicio.guardar(producto);
    }

    @Operation(summary = "Eliminar un producto",
            description = "Elimina un producto registrado por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Producto eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @DeleteMapping("/{id}")
  //  @PreAuthorize("hasAnyRole('ADMIN', 'USER')") // Permite el paso si tiene "ROLE_ADMIN" o "ROLE_USER"
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID del producto", example = "1")
            @PathVariable Long id
    ){
       servicio.eliminarProducto(id);
       return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Actualizar un producto",
            description = "Actualiza los datos de un producto existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })

    //@PreAuthorize("hasAuthority('EDIT_PRODUCT')")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Producto actualizar(
            @Parameter(description = "ID del producto", example = "1")
            @PathVariable Long id,
            @RequestBody Producto producto
    ) {
        producto.setId(id);
        return servicio.actualizar(producto);
    }

}
