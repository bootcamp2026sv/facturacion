package com.bootcamp.facturacion.repository;


import com.bootcamp.facturacion.models.auth.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {}