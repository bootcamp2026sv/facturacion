package com.bootcamp.facturacion.controllers;

import com.bootcamp.facturacion.models.Departamento;
import com.bootcamp.facturacion.services.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoService servicio;

    @GetMapping
    public List<Departamento> listadoDepartamentos(){
        return servicio.listadoDepartamentos();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Departamento guardar(@RequestBody Departamento depto){
        return servicio.guardar(depto);
    }



}
