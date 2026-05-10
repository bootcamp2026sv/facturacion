package com.bootcamp.facturacion.controllers;

import com.bootcamp.facturacion.models.Departamento;
import com.bootcamp.facturacion.services.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
