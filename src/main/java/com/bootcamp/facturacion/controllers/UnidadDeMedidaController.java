package com.bootcamp.facturacion.controllers;

import com.bootcamp.facturacion.models.UnidadDeMedida;
import com.bootcamp.facturacion.services.UnidadDeMedidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/UnidadDeMedidas")
public class UnidadDeMedidaController {

    @Autowired
    private UnidadDeMedidaService servicio;

    
    //GET http://localhost:8080/api/v1/UnidadDeMedidas
    @GetMapping
    public List<UnidadDeMedida> listadoUnidadDeMedidas(){
        return servicio.listadoUnidadDeMedida();
    }

    //GET http://localhost:8080/api/v1/UnidadDeMedidas/4
    @GetMapping("/{id}")
    public UnidadDeMedida unUnidadDeMedida(@PathVariable Long id){
        return servicio.unaUnidadDeMedida(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UnidadDeMedida guardar(@RequestBody UnidadDeMedida unidadDeMedida){
        return servicio.guardar(unidadDeMedida);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
    //http://localhost:8080/api/v1/UnidadDeMedidas/1
       servicio.eliminarUnidadDeMedida(id);
       return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UnidadDeMedida actualizar(@PathVariable Long id, @RequestBody UnidadDeMedida unidadDeMedida) {
        unidadDeMedida.setId(id);
        return servicio.actualizar(unidadDeMedida);
    }




}
