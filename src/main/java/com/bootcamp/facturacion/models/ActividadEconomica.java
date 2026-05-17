package com.bootcamp.facturacion.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "catActividadesEconomicas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActividadEconomica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "codActividad", nullable = false, unique = true)
    private String codActividad; // no nulo

    @Column(name = "descActividad", nullable = false)
    private String descActividad; // no nulo


    @Column(name = "activo", nullable = false)
    private boolean activo; // soft delete
}
