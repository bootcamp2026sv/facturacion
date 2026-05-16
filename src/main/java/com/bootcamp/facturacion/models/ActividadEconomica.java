package com.bootcamp.facturacion.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cat_actividadesEconomicas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActividadEconomica {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "codActividad", nullable = false)
    private String codActividad; // no nulo

    @Column(name = "descActividad", nullable = false)
    private String descActividad; // no nulo
}
