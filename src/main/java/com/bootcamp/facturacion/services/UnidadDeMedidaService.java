package com.bootcamp.facturacion.services;

import com.bootcamp.facturacion.models.UnidadDeMedida;
import com.bootcamp.facturacion.repository.ProductoRepository;
import com.bootcamp.facturacion.repository.UnidadDeMedidaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadDeMedidaService {

    private final UnidadDeMedidaRepository repo;

    public UnidadDeMedidaService(UnidadDeMedidaRepository repo) {
        this.repo = repo;
    }

    public List<UnidadDeMedida> listadoUnidadDeMedida(){
        return repo.findAll();
    }

    public UnidadDeMedida unaUnidadDeMedida(Long id){
        return repo.findById(id).get();
    }



    public UnidadDeMedida guardar(UnidadDeMedida um){

        return repo.save(um);
    }

    public void eliminarUnidadDeMedida(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("No se encontró la unidad de medida con el ID: " + id);
        }
        repo.deleteById(id);
    }

    public UnidadDeMedida actualizar(UnidadDeMedida um) {
        return repo.save(um);
    }

}
