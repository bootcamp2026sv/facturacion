package com.bootcamp.facturacion.services;

import com.bootcamp.facturacion.models.Distrito;
import com.bootcamp.facturacion.repository.DistritoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistritoService {

    private final DistritoRepository repo;

    public DistritoService(DistritoRepository repo) {
        this.repo = repo;
    }

    public List<Distrito> listadoDistritos(){
        // select * from Distrito
        return repo.findAll();
    }

    public Distrito unDistrito(Long id){
        // select * from Distrito
        return repo.findById(id).get();
    }

    public Distrito guardar(Distrito distrito){

        return repo.save(distrito);
    }

    public void eliminarDistrito(Long id) {
        // Verificamos si existe antes de intentar borrar
        //Implementar opcional SOFTDELETE
        if (!repo.existsById(id)) {
            throw new RuntimeException("No se encontró el Distrito con el ID: " + id);
        }
        repo.deleteById(id);
    }

    public Distrito actualizar(Distrito distrito) {
        // UPDATE Distrito SET codigo=?, nombre=? WHERE id=?
        return repo.save(distrito);
        //save cuando la entidad lleva id, hace update y cuando no hace insert
    }



}
