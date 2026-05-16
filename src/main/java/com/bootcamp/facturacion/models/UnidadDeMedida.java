package com.bootcamp.facturacion.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "catUnidadesDeMedida")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnidadDeMedida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "codigo", nullable = false)
    private int codUnidad; // no nulo

    @Column(name = "descripcion", nullable = false)
    private String descUnidad; // no nulo
}
