package com.bootcamp.facturacion;

import com.bootcamp.facturacion.models.Departamento;
import com.bootcamp.facturacion.repository.DepartamentoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class InspectDeptTest {

    @Autowired
    private DepartamentoRepository repo;

    @Test
    public void inspect() {
        System.out.println("=== DEPARTAMENTOS EN BD ===");
        List<Departamento> depts = repo.findAll();
        for (Departamento d : depts) {
            System.out.printf("ID: %d, Codigo: %s, Nombre: %s\n", d.getId(), d.getCodigo(), d.getNombre());
        }
    }
}
