package com.bootcamp.facturacion.services;

import com.bootcamp.facturacion.models.Cliente;
import com.bootcamp.facturacion.models.Departamento;
import com.bootcamp.facturacion.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository repo;

    public ClienteService(ClienteRepository repo) {
        this.repo = repo;
    }

    public List<Cliente> listadoClientes(){
        return repo.findAll();
    }

    public Cliente unCliente(Long id){
        return repo.findById(id).get();
    }

    public Cliente guardar(Cliente cliente){

        return repo.save(cliente);
    }

    public void eliminarCliente(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("No se encontró el cliente con el ID: " + id);
        }
        repo.deleteById(id);
    }

    public Cliente actualizar(Cliente cliente) {
        return repo.save(cliente);
    }

}
