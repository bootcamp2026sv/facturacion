package com.bootcamp.facturacion.services.auth;

import com.bootcamp.facturacion.dto.UsuarioDTO;
import com.bootcamp.facturacion.models.auth.Usuario;
import com.bootcamp.facturacion.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario registrarUsuario(UsuarioDTO dto) {
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombreUsuario(dto.getNombreUsuario());

        // 1. Encriptar la contraseña de texto plano
        String passwordEncriptado = passwordEncoder.encode(dto.getContrasena());
        // 2. Asignar el hash al usuario
        nuevoUsuario.setContrasena(passwordEncriptado);
        // 3. Guardar en la base de datos
        return usuarioRepository.save(nuevoUsuario);
    }

}
