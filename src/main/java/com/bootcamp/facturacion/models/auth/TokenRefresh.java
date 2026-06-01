package com.bootcamp.facturacion.models.auth;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "tokens_refresh")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenRefresh {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Column(unique = true, nullable = false, length = 255)
    private String token;

    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private Instant expiraEn;

    @Builder.Default
    @Column(nullable = false)
    private boolean revocado = false;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant creadoEn;
}
