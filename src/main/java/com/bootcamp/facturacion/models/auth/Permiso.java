package com.bootcamp.facturacion.models.auth;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "permisos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Permiso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Column(unique = true, nullable = false, length = 100)
    private String nombre;

    @Column(length = 200)
    private String descripcion;
}

