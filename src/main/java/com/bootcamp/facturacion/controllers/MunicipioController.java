package com.bootcamp.facturacion.controllers;

import com.bootcamp.facturacion.models.Municipio;
import com.bootcamp.facturacion.services.DepartamentoService;
import com.bootcamp.facturacion.services.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/municipios")
public class MunicipioController {

    @Autowired
    private MunicipioService servicio;


    @GetMapping
    public List<Municipio> listadoDepartamentos(){
        return servicio.listadoMunicipios();
    }



    //GET http://localhost:8080/api/v1/departamentos/4
    @GetMapping("/{id}")
    public Municipio unDepartamento(@PathVariable Long id){
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
