package com.bootcamp.facturacion.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "departamentos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Departamento {
    //Las propiedades se usan por medio de getter y setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "codigo", nullable = false)
    private String Codigo;

    @Column(name = "nombre", nullable = false)
    private String Nombre;

}
