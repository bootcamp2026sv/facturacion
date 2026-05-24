package com.bootcamp.facturacion.controllers;

import com.bootcamp.facturacion.models.Municipio;
import com.bootcamp.facturacion.services.DepartamentoService;
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
@Tag(name = "Municipios", description = "Gestion municipios")
public class MunicipioController {

    @Autowired
    private MunicipioService servicio;


    @Operation(summary = "Obtener todos los municipios",description = "Devuelve lista con todos los municipios registrados")
    @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    @GetMapping
    public List<Municipio> listadoDepartamentos(){
        return servicio.listadoMunicipios();
    }



    //GET http://localhost:8080/api/v1/departamentos/4
    @Operation(summary = "Obtener un municipio",description = "Devuelve un municipio registrado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Municipio obtenido exitosamente"),
            @ApiResponse(responseCode = "404", description = "Municipio no encontrado, revisar x catalogo")
    })
    @GetMapping("/{id}")
    public Municipio unDepartamento(
            @Parameter(description = "ID del municipio", example = "1")
            @PathVariable Long id
    )
    {
        return servicio.unMunicipio(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Municipio guardar(@RequestBody Municipio depto){
        return servicio.guardar(depto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
    //http://localhost:8080/api/v1/departamentos/1
       servicio.eliminarMunicipio(id);
       return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Municipio actualizar(@PathVariable Long id, @RequestBody Municipio depto) {
        depto.setId(id);
        return servicio.actualizar(depto);
    }

}
