package com.bootcamp.facturacion.controllers;

import com.bootcamp.facturacion.models.Producto;
import com.bootcamp.facturacion.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/Productos")
public class ProductoController {

    @Autowired
    private ProductoService servicio;

    
    //GET http://localhost:8080/api/v1/Productos
    @GetMapping
    public List<Producto> listadoProductos(){
        return servicio.listadoProductos();
    }

    //GET http://localhost:8080/api/v1/Productos/4
    @GetMapping("/{id}")
    public Producto unProducto(@PathVariable Long id){
        return servicio.unProducto(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Producto guardar(@RequestBody Producto producto){
        return servicio.guardar(producto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
    //http://localhost:8080/api/v1/Productos/1
       servicio.eliminarProducto(id);
       return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Producto actualizar(@PathVariable Long id, @RequestBody Producto producto) {
        producto.setId(id);
        return servicio.actualizar(producto);
    }




}
