package com.bootcamp.facturacion.security;

import com.bootcamp.facturacion.models.auth.Usuario;
import com.bootcamp.facturacion.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        return usuarioRepository.findByNombreUsuario(usernameOrEmail)
                .orElseGet(() -> usuarioRepository.findByCorreo(usernameOrEmail)
                        .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con: " + usernameOrEmail)));
    }
}
