package com.bootcamp.facturacion.services;

import com.bootcamp.facturacion.models.Cliente;
import com.bootcamp.facturacion.models.Comercio;
import com.bootcamp.facturacion.repository.ClienteRepository;
import com.bootcamp.facturacion.repository.ComercioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComercioService {

    private final ComercioRepository repo;

    public ComercioService(ComercioRepository repo) {
        this.repo = repo;
    }

    public List<Comercio> listadoComercios(){
        return repo.findAll();
    }

    public Comercio unComercio(Long id){
        return repo.findById(id).get();
    }

    public Comercio guardar(Comercio comercio){

        return repo.save(comercio);
    }

    public void eliminarComercio(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("No se encontró el comercio con el ID: " + id);
        }
        repo.deleteById(id);
    }

    public Comercio actualizar(Comercio comercio) {
        return repo.save(comercio);
    }

}
