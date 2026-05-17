package com.bootcamp.facturacion.controllers;

import com.bootcamp.facturacion.models.Cliente;
import com.bootcamp.facturacion.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/Clientes")
public class ClienteController {

    @Autowired
    private ClienteService servicio;

    
    //GET http://localhost:8080/api/v1/Clientes
    @GetMapping
    public List<Cliente> listadoClientes(){
        return servicio.listadoClientes();
    }

    //GET http://localhost:8080/api/v1/Clientes/4
    @GetMapping("/{id}")
    public Cliente unCliente(@PathVariable Long id){
        return servicio.unCliente(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente guardar(@RequestBody Cliente cliente){
        return servicio.guardar(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
    //http://localhost:8080/api/v1/Clientes/1
       servicio.eliminarCliente(id);
       return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente actualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        cliente.setId(id);
        return servicio.actualizar(cliente);
    }




}
