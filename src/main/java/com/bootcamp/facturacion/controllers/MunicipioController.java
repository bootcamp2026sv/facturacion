package com.bootcamp.facturacion.controllers;

import com.bootcamp.facturacion.dto.MunicipioDTO;
import com.bootcamp.facturacion.models.Municipio;
import com.bootcamp.facturacion.services.MunicipioService;
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
@RequestMapping(value = "api/v1/municipios")
@Tag(name = "Municipios", description = "Gestión de municipios del catálogo geográfico de El Salvador")
public class MunicipioController {

    @Autowired
    private MunicipioService servicio;

    @Operation(summary = "Obtener todos los municipios",
            description = "Devuelve lista con todos los municipios registrados, incluyendo sus distritos asociados")
    @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    @GetMapping
    public List<Municipio> listadoMunicipios(){
        return servicio.listadoMunicipios();
    }

    @Operation(summary = "Obtener un municipio",
            description = "Devuelve un municipio registrado por su ID, incluyendo sus distritos asociados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Municipio obtenido exitosamente"),
            @ApiResponse(responseCode = "404", description = "Municipio no encontrado")
    })
    @GetMapping("/{id}")
    public Municipio unMunicipio(
            @Parameter(description = "ID del municipio", example = "1")
            @PathVariable Long id
    ){
        return servicio.unMunicipio(id);
    }

    @Operation(summary = "Crear un municipio",
            description = "Registra un nuevo municipio en el sistema. Requiere código, nombre y departamento_id")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Municipio creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Municipio guardar(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "JSON del municipio a registrar",
                    required = true,
                    content = @io.swagger.v3.oas.annotations.media.Content(
                            mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = Municipio.class),
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                    name = "MunicipioEjemplo",
                                    summary = "Ejemplo de registro de municipio",
                                    value = """
                                    {
                                      "codigo": "02",
                                      "nombre": "San Salvador",
                                      "departamento": { "id": 1 }
                                    }
                                    """
                            )
                    )
            )
            @RequestBody MunicipioDTO municipio
    ){


        return servicio.guardar(municipio);
    }

    @Operation(summary = "Eliminar un municipio",
            description = "Elimina un municipio registrado por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Municipio eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Municipio no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID del municipio", example = "1")
            @PathVariable Long id
    ){
       servicio.eliminarMunicipio(id);
       return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Actualizar un municipio",
            description = "Actualiza los datos de un municipio existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Municipio actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Municipio no encontrado")
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Municipio actualizar(
            @Parameter(description = "ID del municipio", example = "1")
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "JSON del municipio a actualizar",
                    required = true,
                    content = @io.swagger.v3.oas.annotations.media.Content(
                            mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = Municipio.class),
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                    name = "MunicipioActualizarEjemplo",
                                    summary = "Ejemplo de actualización de municipio",
                                    value = """
                                    {
                                      "codigo": "02",
                                      "nombre": "San Salvador Centro",
                                      "departamento": { "id": 1 }
                                    }
                                    """
                            )
                    )
            )
            @RequestBody Municipio municipio
    ) {
        municipio.setId(id);
        return servicio.actualizar(municipio);
    }

}
