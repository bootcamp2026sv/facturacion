package com.bootcamp.facturacion.dto;

import com.bootcamp.facturacion.models.auth.Rol;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO {

    private Long id;
    private String nombreUsuario;
    private String correo;
    private String contrasena;
    private boolean habilitado;

}

