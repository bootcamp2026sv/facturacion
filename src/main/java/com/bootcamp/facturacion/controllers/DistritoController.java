package com.bootcamp.facturacion.controllers;

import com.bootcamp.facturacion.models.Distrito;
import com.bootcamp.facturacion.services.DistritoService;
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
@RequestMapping(value = "api/v1/distritos")
@Tag(name = "Distritos", description = "Gestión de distritos del catálogo geográfico de El Salvador")
public class DistritoController {

    @Autowired
    private DistritoService servicio;

    @Operation(summary = "Obtener todos los distritos",
            description = "Devuelve lista con todos los distritos registrados")
    @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    @GetMapping
    public List<Distrito> listadoDistritos(){
        return servicio.listadoDistritos();
    }

    @Operation(summary = "Obtener un distrito",
            description = "Devuelve un distrito registrado por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Distrito obtenido exitosamente"),
            @ApiResponse(responseCode = "404", description = "Distrito no encontrado")
    })
    @GetMapping("/{id}")
    public Distrito unDistrito(
            @Parameter(description = "ID del distrito", example = "1")
            @PathVariable Long id
    ){
        return servicio.unDistrito(id);
    }

    @Operation(summary = "Crear un distrito",
            description = "Registra un nuevo distrito en el sistema. Requiere código, nombre y municipio_id")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Distrito creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Distrito guardar(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "JSON del distrito a registrar",
                    required = true,
                    content = @io.swagger.v3.oas.annotations.media.Content(
                            mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = Distrito.class),
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                    name = "DistritoEjemplo",
                                    summary = "Ejemplo de registro de distrito",
                                    value = """
                                    {
                                      "codigo": "01",
                                      "nombre": "San Salvador Centro",
                                      "municipio": { "id": 1 }
                                    }
                                    """
                            )
                    )
            )
            @RequestBody Distrito distrito
    ){
        return servicio.guardar(distrito);
    }

    @Operation(summary = "Eliminar un distrito",
            description = "Elimina un distrito registrado por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Distrito eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Distrito no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID del distrito", example = "1")
            @PathVariable Long id
    ){
       servicio.eliminarDistrito(id);
       return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Actualizar un distrito",
            description = "Actualiza los datos de un distrito existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Distrito actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Distrito no encontrado")
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Distrito actualizar(
            @Parameter(description = "ID del distrito", example = "1")
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "JSON del distrito a actualizar",
                    required = true,
                    content = @io.swagger.v3.oas.annotations.media.Content(
                            mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = Distrito.class),
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                    name = "DistritoActualizarEjemplo",
                                    summary = "Ejemplo de actualización de distrito",
                                    value = """
                                    {
                                      "codigo": "01",
                                      "nombre": "San Salvador Norte",
                                      "municipio": { "id": 1 }
                                    }
                                    """
                            )
                    )
            )
            @RequestBody Distrito distrito
    ) {
        distrito.setId(id);
        return servicio.actualizar(distrito);
    }

}
