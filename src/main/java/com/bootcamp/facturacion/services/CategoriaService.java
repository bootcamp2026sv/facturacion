package com.bootcamp.facturacion.services;

import com.bootcamp.facturacion.models.Categoria;
import com.bootcamp.facturacion.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository repo;

    public CategoriaService(CategoriaRepository repo) {
        this.repo = repo;
    }

    public List<Categoria> listadoCategorias() {
        return repo.findAll();
    }

    public Categoria unaCategoria(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró la categoría con el ID: " + id));
    }

    public Categoria guardar(Categoria categoria) {
        return repo.save(categoria);
    }

    public Categoria actualizar(Categoria categoria) {
        if (!repo.existsById(categoria.getId())) {
            throw new RuntimeException("No se encontró la categoría con el ID: " + categoria.getId());
        }
        return repo.save(categoria);
    }

    public void eliminarCategoria(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("No se encontró la categoría con el ID: " + id);
        }
        repo.deleteById(id);
    }
}
