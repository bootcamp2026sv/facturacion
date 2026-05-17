package com.bootcamp.facturacion.controllers;

import com.bootcamp.facturacion.models.ActividadEconomica;
import com.bootcamp.facturacion.services.ActividadEconomicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/ActividadEconomicas") //ENDPOINT  / RECURSO
public class ActividadEconomicaController {

    @Autowired
    private ActividadEconomicaService servicio;

    
    //GET http://localhost:8080/api/v1/ActividadEconomicas
    @GetMapping
    public List<ActividadEconomica> listadoActividadEconomicas(){
        return servicio.listadoActividades();
    }

    //GET http://localhost:8080/api/v1/ActividadEconomicas/4
    @GetMapping("/{id}")
    public ActividadEconomica unActividadEconomica(@PathVariable Long id){
        return servicio.unActividad(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ActividadEconomica guardar(@RequestBody ActividadEconomica actividadEconomica){
        return servicio.guardar(actividadEconomica);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
    //http://localhost:8080/api/v1/ActividadEconomicas/1
       servicio.eliminarActividad(id);
       return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ActividadEconomica actualizar(@PathVariable Long id, @RequestBody ActividadEconomica actividadEconomica) {
        actividadEconomica.setId(id);
        return servicio.actualizar(actividadEconomica);
    }




}
