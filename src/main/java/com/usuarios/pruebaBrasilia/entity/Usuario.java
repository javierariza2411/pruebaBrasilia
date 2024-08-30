package com.usuarios.pruebaBrasilia.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String nombreUsuario;

    @OneToMany(mappedBy = "usuario",cascade=CascadeType.ALL)
    private Set<Tarea> tarea=new HashSet<>();


}
