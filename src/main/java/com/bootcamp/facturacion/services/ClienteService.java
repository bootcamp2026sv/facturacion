package com.bootcamp.facturacion.services;

import com.bootcamp.facturacion.dto.ClienteDTO;
import com.bootcamp.facturacion.models.ActividadEconomica;
import com.bootcamp.facturacion.models.Cliente;
import com.bootcamp.facturacion.models.Departamento;
import com.bootcamp.facturacion.models.Municipio;
import com.bootcamp.facturacion.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository repo;

    public ClienteService(ClienteRepository repo) {
        this.repo = repo;
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

        Municipio municipio= new Municipio();
        municipio.setId(clienteDTO.getMunicipio_id());

        ActividadEconomica actividad = new ActividadEconomica();
        actividad.setId(clienteDTO.getActividadEconomica_id());
        actividad.setActivo(true);

        cliente.setMunicipio(municipio);
        cliente.setActividadEconomica(actividad);

        return repo.save(cliente);
    }

    public void eliminarCliente(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("No se encontró el cliente con el ID: " + id);
        }
        repo.deleteById(id);
    }

    public Cliente actualizar(Cliente cliente) {
        return repo.save(cliente);
    }

}
