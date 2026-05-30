package com.bootcamp.facturacion.config;

import com.bootcamp.facturacion.models.auth.Permiso;
import com.bootcamp.facturacion.models.auth.Rol;
import com.bootcamp.facturacion.models.auth.Usuario;
import com.bootcamp.facturacion.repository.PermisoRepository;
import com.bootcamp.facturacion.repository.RolRepository;
import com.bootcamp.facturacion.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PermisoRepository permisoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // 1. Crear permisos si no existen
        Permiso readProd = crearPermisoSiNoExiste("READ_PRODUCT", "Permite ver productos");
        Permiso writeProd = crearPermisoSiNoExiste("WRITE_PRODUCT", "Permite crear/editar/eliminar productos");

        // 2. Crear roles y asociar permisos
        Rol adminRol = crearRolSiNoExiste("ADMIN", "Administrador con todos los permisos", Set.of(readProd, writeProd));
        Rol userRol = crearRolSiNoExiste("USER", "Usuario regular con permisos de lectura", Set.of(readProd));

        // 3. Crear usuario administrador inicial
        if (!usuarioRepository.existsByNombreUsuario("admin")) {
            Usuario admin = Usuario.builder()
                    .nombreUsuario("admin")
                    .correo("admin@facturacion.com")
                    .contrasena(passwordEncoder.encode("admin123"))
                    .habilitado(true)
                    .roles(Set.of(adminRol))
                    .build();
            usuarioRepository.save(admin);
            System.out.println(">>> Usuario administrador por defecto creado (admin / admin123)");
        }
    }

    private Permiso crearPermisoSiNoExiste(String nombre, String descripcion) {
        return permisoRepository.findByNombre(nombre)
                .orElseGet(() -> permisoRepository.save(
                        Permiso.builder().nombre(nombre).descripcion(descripcion).build()
                ));
    }

    private Rol crearRolSiNoExiste(String nombre, String descripcion, Set<Permiso> permisos) {
        return rolRepository.findByNombre(nombre)
                .orElseGet(() -> rolRepository.save(
                        Rol.builder().nombre(nombre).descripcion(descripcion).permisos(permisos).build()
                ));
    }
}
