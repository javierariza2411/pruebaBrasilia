package com.usuarios.pruebaBrasilia.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="tarea")
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   private String titulo;
   private String descripcion;
   private Boolean estado;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name="usuario_id", nullable = false)
   private Usuario usuario;



}
