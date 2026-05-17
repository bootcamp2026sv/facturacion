package com.bootcamp.facturacion.services;

import com.bootcamp.facturacion.models.Producto;
import com.bootcamp.facturacion.repository.ActividadEconomicaRepository;
import com.bootcamp.facturacion.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository repo;

    public ProductoService(ProductoRepository repo) {
        this.repo = repo;
    }

    public List<Producto> listadoProductos(){
        return repo.findAll();
    }

    public Producto unProducto(Long id){
        return repo.findById(id).get();
    }


    public Producto guardar(Producto producto){

        return repo.save(producto);
    }

    public void eliminarProducto(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("No se encontró el producto con el ID: " + id);
        }
        repo.deleteById(id);
    }

    public Producto actualizar(Producto producto) {
        return repo.save(producto);
    }

}
