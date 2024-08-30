package com.usuarios.pruebaBrasilia.jpa.repository;

import com.usuarios.pruebaBrasilia.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
}
