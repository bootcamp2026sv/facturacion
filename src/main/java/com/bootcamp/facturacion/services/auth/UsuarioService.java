package com.bootcamp.facturacion.services.auth;

import com.bootcamp.facturacion.dto.UsuarioDTO;
import com.bootcamp.facturacion.models.auth.Rol;
import com.bootcamp.facturacion.models.auth.Usuario;
import com.bootcamp.facturacion.repository.RolRepository;
import com.bootcamp.facturacion.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario registrarUsuario(UsuarioDTO dto) {
        if (usuarioRepository.existsByNombreUsuario(dto.getNombreUsuario())) {
            throw new RuntimeException("El nombre de usuario ya está registrado");
        }
        if (usuarioRepository.existsByCorreo(dto.getCorreo())) {
            throw new RuntimeException("El correo electrónico ya está registrado");
        }

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombreUsuario(dto.getNombreUsuario());
        nuevoUsuario.setCorreo(dto.getCorreo());
        nuevoUsuario.setHabilitado(true);

        // 1. Encriptar la contraseña de texto plano
        String passwordEncriptado = passwordEncoder.encode(dto.getContrasena());
        // 2. Asignar el hash al usuario
        nuevoUsuario.setContrasena(passwordEncriptado);

        // 3. Asignar rol por defecto USER
        Set<Rol> roles = new HashSet<>();
        Rol defaultRol = rolRepository.findByNombre("USER")
                .orElseGet(() -> rolRepository.save(
                        Rol.builder()
                                .nombre("USER")
                                .descripcion("Usuario regular")
                                .build()
                ));
        roles.add(defaultRol);
        nuevoUsuario.setRoles(roles);

        // 4. Guardar en la base de datos
        return usuarioRepository.save(nuevoUsuario);
    }

}
