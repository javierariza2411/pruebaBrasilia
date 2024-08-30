package com.usuarios.pruebaBrasilia.services.impl;

import com.usuarios.pruebaBrasilia.dto.TareaRequestDTO;
import com.usuarios.pruebaBrasilia.dto.TareaResponseDTO;
import com.usuarios.pruebaBrasilia.dto.UsuarioRequestDTO;
import com.usuarios.pruebaBrasilia.dto.UsuarioResponseDTO;
import com.usuarios.pruebaBrasilia.entity.Usuario;
import com.usuarios.pruebaBrasilia.exceptions.ResourceNotFoundException;
import com.usuarios.pruebaBrasilia.jpa.repository.UsuarioRepository;
import com.usuarios.pruebaBrasilia.services.TareaService;
import com.usuarios.pruebaBrasilia.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;

    @Transactional
    @Override
    public UsuarioResponseDTO crear(UsuarioRequestDTO usuario) {

        Usuario usuarioGuardado=null;

        Usuario usuario1= Usuario.builder()
                .nombreUsuario(usuario.getNombre()).build();


        try {
             usuarioGuardado=repository.save(usuario1);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre de usuario ya existe");
        }

        return UsuarioResponseDTO.builder().nombre(usuarioGuardado.getNombreUsuario())
                .id(usuarioGuardado.getId()).build();
    }

    @Transactional(readOnly=true)
    @Override
    public List<UsuarioResponseDTO> buscarTodos() {

        List<Usuario> usuariosGuardados = repository.findAll();

       return usuariosGuardados.stream().map(usuario -> UsuarioResponseDTO.builder().nombre(usuario.getNombreUsuario())
                .id(usuario.getId()).build()).toList();

    }

    @Transactional(readOnly=true)
    @Override
    public UsuarioResponseDTO buscarUsuario(Integer usuarioId) {

        Usuario usuario = repository.findById(usuarioId).orElseThrow(
                () -> new ResourceNotFoundException("Usuario", "id", usuarioId.toString())
        );

        return UsuarioResponseDTO.builder().nombre(usuario.getNombreUsuario())
                .id(usuario.getId()).build();
    }
}
