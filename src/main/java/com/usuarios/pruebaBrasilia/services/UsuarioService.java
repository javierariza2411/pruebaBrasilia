package com.usuarios.pruebaBrasilia.services;

import com.usuarios.pruebaBrasilia.dto.UsuarioRequestDTO;
import com.usuarios.pruebaBrasilia.dto.UsuarioResponseDTO;

import java.util.List;

public interface UsuarioService {


    UsuarioResponseDTO crear(UsuarioRequestDTO usuario);

    List<UsuarioResponseDTO> buscarTodos();

    UsuarioResponseDTO buscarUsuario(Integer usuarioId);

}
