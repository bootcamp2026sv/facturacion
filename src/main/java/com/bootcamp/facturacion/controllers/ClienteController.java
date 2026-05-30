package com.bootcamp.facturacion.controllers;

import com.bootcamp.facturacion.dto.ClienteDTO;
import com.bootcamp.facturacion.models.Cliente;
import com.bootcamp.facturacion.services.ClienteService;
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
@RequestMapping(value = "api/v1/Clientes")
@Tag(name = "Clientes", description = "Gestión de clientes")
public class ClienteController {

    @Autowired
    private ClienteService servicio;

    @Operation(summary = "Obtener todos los clientes",
            description = "Devuelve lista con todos los clientes registrados")
    @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    @GetMapping
    public List<Cliente> listadoClientes(){
        return servicio.listadoClientes();
    }

    @Operation(summary = "Obtener un cliente",
            description = "Devuelve un cliente registrado por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente obtenido exitosamente"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @GetMapping("/{id}")
    public Cliente unCliente(
            @Parameter(description = "ID del cliente", example = "1")
            @PathVariable Long id
    ){
        return servicio.unCliente(id);
    }

    @Operation(summary = "Crear un cliente",
            description = "Registra un nuevo cliente en el sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cliente creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente guardar(@RequestBody ClienteDTO cliente){
        return servicio.guardar(cliente);
    }

    @Operation(summary = "Eliminar un cliente",
            description = "Elimina un cliente registrado por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Cliente eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID del cliente", example = "1")
            @PathVariable Long id
    ){
       servicio.eliminarCliente(id);
       return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Actualizar un cliente",
            description = "Actualiza los datos de un cliente existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente actualizar(
            @Parameter(description = "ID del cliente", example = "1")
            @PathVariable Long id,
            @RequestBody ClienteDTO cliente
    ) {
        cliente.setId(id);
        return servicio.actualizar(cliente);
    }

}
