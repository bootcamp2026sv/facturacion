package com.bootcamp.facturacion.repository;

import com.bootcamp.facturacion.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    //NamedQuery https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
    //este metodo consulta por NumDocumento
    Optional<Cliente> findByNumDocumento(String numDocumento);
    Optional<Cliente> findFirstByNumDocumentoIsNull();
    Optional<Cliente> findFirstByNumDocumento(String numDocumento);

}