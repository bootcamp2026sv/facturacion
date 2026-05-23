package com.bootcamp.facturacion.services;

import com.bootcamp.facturacion.dto.ComercioDTO;
import com.bootcamp.facturacion.models.ActividadEconomica;
import com.bootcamp.facturacion.models.Cliente;
import com.bootcamp.facturacion.models.Comercio;
import com.bootcamp.facturacion.models.Municipio;
import com.bootcamp.facturacion.repository.ClienteRepository;
import com.bootcamp.facturacion.repository.ComercioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComercioService {

    private final ComercioRepository repo;

    public ComercioService(ComercioRepository repo) {
        this.repo = repo;
    }

    public List<Comercio> listadoComercios(){
        return repo.findAll();
    }

    public Comercio unComercio(Long id){
        return repo.findById(id).get();
    }

    public Comercio guardar(ComercioDTO comercioDTO){

        Comercio comercio = new Comercio();

        comercio.setNombre(comercioDTO.getNombre());
        comercio.setNombreComercial(comercioDTO.getNombreComercial());
        comercio.setNit(comercioDTO.getNit());
        comercio.setNrc(comercioDTO.getNrc());
        comercio.setTelefono(comercioDTO.getTelefono());
        comercio.setCodEstableMH(comercioDTO.getCodEstableMH());
        comercio.setCodPuntoVentaMH(comercioDTO.getCodPuntoVentaMH());
        comercio.setComplementoDireccion(comercioDTO.getComplementoDireccion());
        comercio.setTipoEstablecimiento(comercioDTO.getTipoEstablecimiento());
        comercio.setGranContribuyente(comercioDTO.isGranContribuyente());

        Municipio municipio= new Municipio();
        municipio.setId(comercioDTO.getMunicipio_id());

        ActividadEconomica actividad = new ActividadEconomica();
        actividad.setId(comercioDTO.getActividadEconomica_id());
        actividad.setActivo(true);

        comercio.setMunicipio(municipio);
        comercio.setActividadEconomica(actividad);

        return repo.save(comercio);
    }

    public void eliminarComercio(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("No se encontró el comercio con el ID: " + id);
        }
        repo.deleteById(id);
    }

    public Comercio actualizar(ComercioDTO comercioDTO) {
        Comercio comercio = new Comercio();

        comercio.setId(comercioDTO.getId());
        comercio.setNombre(comercioDTO.getNombre());
        comercio.setNombreComercial(comercioDTO.getNombreComercial());
        comercio.setNit(comercioDTO.getNit());
        comercio.setNrc(comercioDTO.getNrc());
        comercio.setTelefono(comercioDTO.getTelefono());
        comercio.setCodEstableMH(comercioDTO.getCodEstableMH());
        comercio.setCodPuntoVentaMH(comercioDTO.getCodPuntoVentaMH());
        comercio.setComplementoDireccion(comercioDTO.getComplementoDireccion());
        comercio.setTipoEstablecimiento(comercioDTO.getTipoEstablecimiento());
        comercio.setGranContribuyente(comercioDTO.isGranContribuyente());

        Municipio municipio= new Municipio();
        municipio.setId(comercioDTO.getMunicipio_id());

        ActividadEconomica actividad = new ActividadEconomica();
        actividad.setId(comercioDTO.getActividadEconomica_id());
        actividad.setActivo(true);

        comercio.setMunicipio(municipio);
        comercio.setActividadEconomica(actividad);
        return repo.save(comercio);
    }

}
