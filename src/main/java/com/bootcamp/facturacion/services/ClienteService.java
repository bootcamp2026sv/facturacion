package com.bootcamp.facturacion.services;

import com.bootcamp.facturacion.dto.ClienteDTO;
import com.bootcamp.facturacion.models.ActividadEconomica;
import com.bootcamp.facturacion.models.Cliente;
import com.bootcamp.facturacion.models.Departamento;
import com.bootcamp.facturacion.models.Distrito;
import com.bootcamp.facturacion.models.Municipio;
import com.bootcamp.facturacion.repository.ClienteRepository;
import com.bootcamp.facturacion.repository.DistritoRepository;
import com.bootcamp.facturacion.repository.ActividadEconomicaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository repo;
    private final DistritoRepository distritoRepo;
    private final ActividadEconomicaRepository actividadRepo;

    public ClienteService(ClienteRepository repo, DistritoRepository distritoRepo, ActividadEconomicaRepository actividadRepo) {
        this.repo = repo;
        this.distritoRepo = distritoRepo;
        this.actividadRepo = actividadRepo;
    }

    public List<Cliente> listadoClientes(){
        return repo.findAll();
    }

    public Cliente unCliente(Long id){
        return repo.findById(id).get();
    }

    public Cliente guardar(ClienteDTO clienteDTO){

        //Covertir el DTO en un Objeto de la entidad correcta segun el repository
        Cliente cliente= new Cliente();

        cliente.setActivo(true);
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setApellidos(clienteDTO.getApellidos());
        cliente.setNombreComercial(clienteDTO.getNombreComercial());
        cliente.setNrc(clienteDTO.getNrc());
        cliente.setTipoDocumento(clienteDTO.getTipoDocumento());
        cliente.setNumDocumento(clienteDTO.getNumDocumento());
        cliente.setTelefono(clienteDTO.getTelefono());
        cliente.setComplementoDireccion(clienteDTO.getComplementoDireccion());
        cliente.setCorreo(clienteDTO.getCorreo());
        cliente.setGranContribuyente(clienteDTO.isGranContribuyente());

        Distrito distrito = null;
        if (clienteDTO.getDistrito_id() != null) {
            distrito = distritoRepo.findById(clienteDTO.getDistrito_id()).orElse(null);
        }

        ActividadEconomica actividad = null;
        if (clienteDTO.getActividadEconomica_id() != null) {
            actividad = actividadRepo.findById(clienteDTO.getActividadEconomica_id()).orElse(null);
        }

        cliente.setDistrito(distrito);
        cliente.setActividadEconomica(actividad);

        return repo.save(cliente);
    }

    public void eliminarCliente(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("No se encontró el cliente con el ID: " + id);
        }
        repo.deleteById(id);
    }

    public Cliente actualizar(ClienteDTO clienteDTO) {

        Cliente cliente= new Cliente();
        cliente.setId(clienteDTO.getId()); //si id null insert, si trae valor update
        cliente.setActivo(clienteDTO.isActivo());
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setApellidos(clienteDTO.getApellidos());
        cliente.setNombreComercial(clienteDTO.getNombreComercial());
        cliente.setNrc(clienteDTO.getNrc());
        cliente.setTipoDocumento(clienteDTO.getTipoDocumento());
        cliente.setNumDocumento(clienteDTO.getNumDocumento());
        cliente.setTelefono(clienteDTO.getTelefono());
        cliente.setComplementoDireccion(clienteDTO.getComplementoDireccion());
        cliente.setCorreo(clienteDTO.getCorreo());
        cliente.setGranContribuyente(clienteDTO.isGranContribuyente());

        Distrito distrito = null;
        if (clienteDTO.getDistrito_id() != null) {
            distrito = distritoRepo.findById(clienteDTO.getDistrito_id()).orElse(null);
        }

        ActividadEconomica actividad = null;
        if (clienteDTO.getActividadEconomica_id() != null) {
            actividad = actividadRepo.findById(clienteDTO.getActividadEconomica_id()).orElse(null);
        }

        cliente.setDistrito(distrito);
        cliente.setActividadEconomica(actividad);

        return repo.save(cliente);
    }

}
