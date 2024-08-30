package com.usuarios.pruebaBrasilia.jpa.repository;

import com.usuarios.pruebaBrasilia.entity.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TareaRepository extends JpaRepository<Tarea,Integer> {

    Optional<Tarea> findByUsuario_Id(Integer id);

    List<Tarea> findTareaByUsuario_Id(Integer id);
}
