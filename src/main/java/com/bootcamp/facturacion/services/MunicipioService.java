package com.bootcamp.facturacion.services;

import com.bootcamp.facturacion.models.Municipio;
import com.bootcamp.facturacion.repository.MunicipioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MunicipioService {

    private final MunicipioRepository repo;

    public MunicipioService(MunicipioRepository repo) {
        this.repo = repo;
    }

    public List<Municipio> listadoMunicipios(){
        // select * from Municipio
        return repo.findAll();
    }

    public Municipio unMunicipio(Long id){
        // select * from Municipio
        return repo.findById(id).get();
    }

    public Municipio guardar(Municipio municipio){

        return repo.save(municipio);
    }

    public void eliminarMunicipio(Long id) {
        // Verificamos si existe antes de intentar borrar
        //Implementar opcional SOFTDELETE
        if (!repo.existsById(id)) {
            throw new RuntimeException("No se encontró el Municipio con el ID: " + id);
        }
        repo.deleteById(id);
    }

    public Municipio actualizar(Municipio municipio) {
        // UPDATE Municipio SET codigo=?, nombre=? WHERE id=?
        return repo.save(municipio);
        //save cuando la entidad lleva id, hace update y cuando no hace insert
    }



}
