package com.bootcamp.facturacion.services;

import com.bootcamp.facturacion.models.DetalleVenta;
import com.bootcamp.facturacion.models.Venta;
import com.bootcamp.facturacion.models.Municipio;
import com.bootcamp.facturacion.repository.VentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaService {

    private final VentaRepository repo;

    public VentaService(VentaRepository repo) {
        this.repo = repo;
    }

    public List<Venta> listadoVentas(){
        return repo.findAll();
    }
    public Venta unaVenta(Long id){
        return repo.findById(id).get();
    }

    public Venta guardar(Venta venta){

        if (venta.getDetallesVenta() != null) {
            for (DetalleVenta detalleVenta : venta.getDetallesVenta()) {
                detalleVenta.setVenta(venta); // ← clave
            }
        }
        return repo.save(venta);
    }

    public Venta actualizar(Venta departamento) {
        return repo.save(departamento);
    }



}
