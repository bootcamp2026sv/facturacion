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
@Tag(name = "Distritos", description = "Gestion distritos")
public class DistritoController {

    @Autowired
    private DistritoService servicio;


    @Operation(summary = "Obtener todos los distritos",
            description = "Devuelve lista con todos los distritos registrados")
    @ApiResponse(responseCode = "200",
            description = "Lista obtenida exitosamente")
    @GetMapping
    public List<Distrito> listadoDistritos(){
        return servicio.listadoDistritos();
    }



    //GET http://localhost:8080/api/v1/distritos/4
    @Operation(summary = "Obtener un municipio",description = "Devuelve un municipio registrado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Distrito obtenido exitosamente"),
            @ApiResponse(responseCode = "404", description = "Distrito no encontrado, revisar x catalogo")
    })
    @GetMapping("/{id}")
    public Distrito unDistrito(
            @Parameter(description = "ID del municipio", example = "1")
            @PathVariable Long id
    )
    {
        return servicio.unDistrito(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Distrito guardar(@RequestBody Distrito depto){
        return servicio.guardar(depto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
    //http://localhost:8080/api/v1/distritos/1
       servicio.eliminarDistrito(id);
       return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Distrito actualizar(@PathVariable Long id, @RequestBody Distrito depto) {
        depto.setId(id);
        return servicio.actualizar(depto);
    }

}
