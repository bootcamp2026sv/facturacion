package com.bootcamp.facturacion.controllers;

import com.bootcamp.facturacion.models.Venta;
import com.bootcamp.facturacion.services.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/Ventas")
public class VentaController {

    @Autowired
    private VentaService servicio;

    
    //GET http://localhost:8080/api/v1/Ventas
    @GetMapping
    public List<Venta> listadoVentas(){
        return servicio.listadoVentas();
    }

    //GET http://localhost:8080/api/v1/Ventas/4
    @GetMapping("/{id}")
    public Venta unVenta(@PathVariable Long id){
        return servicio.unaVenta(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Venta guardar(@RequestBody Venta venta){
        return servicio.guardar(venta);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Venta actualizar(@PathVariable Long id, @RequestBody Venta venta) {
        venta.setId(id);
        return servicio.actualizar(venta);
    }




}
