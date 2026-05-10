package com.bootcamp.facturacion.controllers;

import com.bootcamp.facturacion.models.Departamento;
import com.bootcamp.facturacion.services.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoService servicio;

    //GET http://localhost:8080/api/v1/departamentos
    @GetMapping
    public List<Departamento> listadoDepartamentos(){
        return servicio.listadoDepartamentos();
    }

    //GET http://localhost:8080/api/v1/departamentos/4
    @GetMapping("/{id}")
    public Departamento unDepartamento(@PathVariable Long id){
        return servicio.unDepartamento(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Departamento guardar(@RequestBody Departamento depto){
        return servicio.guardar(depto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
    //http://localhost:8080/api/v1/departamentos/1
       servicio.eliminarDepartamento(id);
       return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Departamento actualizar(@PathVariable Long id, @RequestBody Departamento depto) {
        depto.setId(id);
        return servicio.actualizar(depto);
    }




}
