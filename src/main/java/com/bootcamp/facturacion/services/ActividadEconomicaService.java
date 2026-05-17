package com.bootcamp.facturacion.services;

import com.bootcamp.facturacion.models.ActividadEconomica;
import com.bootcamp.facturacion.models.Comercio;
import com.bootcamp.facturacion.repository.ActividadEconomicaRepository;
import com.bootcamp.facturacion.repository.ComercioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActividadEconomicaService {

    private final ActividadEconomicaRepository repo;

    public ActividadEconomicaService(ActividadEconomicaRepository repo) {
        this.repo = repo;
    }

    public List<ActividadEconomica> listadoActividades(){
        return repo.findAll();
    }

    public ActividadEconomica unActividad(Long id){
        return repo.findById(id).get();
    }

    public ActividadEconomica guardar(ActividadEconomica actividadEconomica){

        return repo.save(actividadEconomica);
    }

    public void eliminarActividad(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("No se encontró la actividad economica con el ID: " + id);
        }
        repo.deleteById(id);
    }

    public ActividadEconomica actualizar(ActividadEconomica actividadEconomica) {
        return repo.save(actividadEconomica);
    }

}
