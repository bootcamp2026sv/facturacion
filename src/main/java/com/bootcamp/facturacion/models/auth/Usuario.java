package com.bootcamp.facturacion.models.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Entidad que representa un usuario del sistema")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "ID autogenerado", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    @Schema(description = "Nombre de usuario único", example = "jperez")
    private String nombreUsuario;

    @Column(unique = true, nullable = false, length = 100)
    @Schema(description = "Correo electrónico único", example = "jperez@correo.com")
    private String correo;

    @Column(nullable = false, length = 255)
    @Schema(description = "Contraseña encriptada", example = "********")
    private String contrasena;

    @Builder.Default
    @Column(nullable = false)
    @Schema(description = "Usuario habilitado", example = "true")
    private boolean habilitado = true;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    @Schema(description = "Fecha de creación", example = "2025-01-01T00:00:00")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    @Schema(description = "Fecha de última actualización", example = "2025-01-01T00:00:00")
    private LocalDateTime updateAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuario_roles",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Schema(description = "Roles asignados al usuario")
    private Set<Rol> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Rol rol : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + rol.getNombre()));
            for (Permiso permiso : rol.getPermisos()) {
                authorities.add(new SimpleGrantedAuthority(permiso.getNombre()));
            }
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return nombreUsuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return habilitado;
    }
}

