package com.bootcamp.facturacion.controllers;

import com.bootcamp.facturacion.models.Comercio;
import com.bootcamp.facturacion.services.ComercioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/Comercios")
public class ComercioController {

    @Autowired
    private ComercioService servicio;

    
    //GET http://localhost:8080/api/v1/Comercios
    @GetMapping
    public List<Comercio> listadoComercios(){
        return servicio.listadoComercios();
    }

    //GET http://localhost:8080/api/v1/Comercios/4
    @GetMapping("/{id}")
    public Comercio unComercio(@PathVariable Long id){
        return servicio.unComercio(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Comercio guardar(@RequestBody Comercio comercio){
        return servicio.guardar(comercio);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
    //http://localhost:8080/api/v1/Comercios/1
       servicio.eliminarComercio(id);
       return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Comercio actualizar(@PathVariable Long id, @RequestBody Comercio comercio) {
        comercio.setId(id);
        return servicio.actualizar(comercio);
    }




}
