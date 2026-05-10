package com.bootcamp.facturacion.services;

import com.bootcamp.facturacion.models.Departamento;
import com.bootcamp.facturacion.models.Municipio;
import com.bootcamp.facturacion.repository.DepartamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoService {

    private final DepartamentoRepository repo;

    public DepartamentoService(DepartamentoRepository repo) {
        this.repo = repo;
    }

    public List<Departamento> listadoDepartamentos(){
        // select * from departamentos
        return repo.findAll();
    }

    public Departamento unDepartamento(Long id){
        // select * from departamentos
        return repo.findById(id).get();
    }

    public Departamento guardar(Departamento departamento){
        //insert into departamento(id, codigo, nombre) values (1,'04','Chalatenango')
        // Asignar la referencia del departamento a cada municipio
        if (departamento.getMunicipios() != null) {
            for (Municipio municipio : departamento.getMunicipios()) {
                municipio.setDepartamento(departamento); // ← clave
            }
        }
        return repo.save(departamento);
    }

    public void eliminarDepartamento(Long id) {
        // Verificamos si existe antes de intentar borrar
        //Implementar opcional SOFTDELETE
        if (!repo.existsById(id)) {
            throw new RuntimeException("No se encontró el departamento con el ID: " + id);
        }
        repo.deleteById(id);
    }

    public Departamento actualizar(Departamento departamento) {
        // UPDATE departamento SET codigo=?, nombre=? WHERE id=?
        return repo.save(departamento);
        //save cuando la entidad lleva id, hace update y cuando no hace insert
    }



}
