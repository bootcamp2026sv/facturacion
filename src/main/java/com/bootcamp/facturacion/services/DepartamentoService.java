package com.bootcamp.facturacion.services;

import com.bootcamp.facturacion.models.Departamento;
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

    public Departamento guardar(Departamento departamento){
        //insert into departamento(id, codigo, nombre) values (1,'04','Chalatenango')
        return repo.save(departamento);
    }

}
