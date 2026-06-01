package com.bootcamp.facturacion.models.auth;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String nombre;

    @Column(length = 200)
    private String descripcion;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "rol_permisos",
            joinColumns = @JoinColumn(name = "rol_id"),
            inverseJoinColumns = @JoinColumn(name = "permiso_id")
    )
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Permiso> permisos = new HashSet<>();
}

