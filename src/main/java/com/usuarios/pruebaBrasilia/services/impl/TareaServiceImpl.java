package com.usuarios.pruebaBrasilia.services.impl;

import com.usuarios.pruebaBrasilia.dto.TareaRequestDTO;
import com.usuarios.pruebaBrasilia.dto.TareaResponseDTO;
import com.usuarios.pruebaBrasilia.dto.UsuarioResponseDTO;
import com.usuarios.pruebaBrasilia.entity.Tarea;
import com.usuarios.pruebaBrasilia.entity.Usuario;
import com.usuarios.pruebaBrasilia.exceptions.BrasiliaAPIException;
import com.usuarios.pruebaBrasilia.exceptions.ResourceNotFoundException;
import com.usuarios.pruebaBrasilia.jpa.repository.TareaRepository;
import com.usuarios.pruebaBrasilia.jpa.repository.UsuarioRepository;
import com.usuarios.pruebaBrasilia.services.TareaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TareaServiceImpl implements TareaService {

    private final UsuarioRepository usuarioRepository;
    private final TareaRepository tareaRepository;


    @Transactional
    @Override
    public TareaResponseDTO crear(Integer usuarioId, TareaRequestDTO tarea) {

        Usuario usuario = getUsuario(usuarioId);

        Tarea tarea1=Tarea.builder().titulo(tarea.getTitulo())
                .descripcion(tarea.getDescripcion())
                .usuario(usuario).estado(false).build();

        Tarea tareaGuardada = tareaRepository.save(tarea1);

        return TareaResponseDTO.builder().
                titulo(tareaGuardada.getTitulo())
                .descripcion(tareaGuardada.getDescripcion())
                .id(tareaGuardada.getId()).estado(tareaGuardada.getEstado() ? "completada":"pendiente").build();

    }


    @Transactional
    @Override
    public TareaResponseDTO cambiarEstado(Integer usuarioId, Integer tareaId) {

        Usuario usuario = getUsuario(usuarioId);


        Tarea tarea = tareaRepository.findById(tareaId).orElseThrow(
                () -> new ResourceNotFoundException("Tarea", "id", tareaId.toString())
        );

        if(!tarea.getUsuario().getId().equals(usuarioId)){

            throw new BrasiliaAPIException(HttpStatus.BAD_REQUEST, "La tarea no pertenece a este usuario");

        }

        tarea.setEstado(true);

        Tarea tareaActualizada = tareaRepository.save(tarea);


        return TareaResponseDTO.builder().
                titulo(tareaActualizada.getTitulo())
                .descripcion(tareaActualizada.getDescripcion())
                .id(tareaActualizada.getId()).estado(tareaActualizada.getEstado() ? "completada":"pendiente").build();

    }

    @Transactional(readOnly=true)
    @Override
    public List<TareaResponseDTO> buscarTareas(Integer usuarioId) {


        Usuario usuario = getUsuario(usuarioId);

        List<Tarea> tareasGuardadas = tareaRepository.findTareaByUsuario_Id(usuarioId);


        return tareasGuardadas.stream().map(tarea -> TareaResponseDTO.builder().titulo(tarea.getTitulo())
                .id(tarea.getId())
                .descripcion(tarea.getDescripcion())
                .estado(tarea.getEstado() ? "completada":"pendiente")
                .build()).toList();


    }

    @Transactional
    @Override
    public void eliminar(Integer usuarioId, Integer tareaId) {

        Usuario usuario = getUsuario(usuarioId);

        Tarea tarea = tareaRepository.findById(tareaId).orElseThrow(
                () -> new ResourceNotFoundException("Tarea", "id", tareaId.toString())
        );

        if(!tarea.getUsuario().getId().equals(usuarioId)){

            throw new BrasiliaAPIException(HttpStatus.BAD_REQUEST, "La tarea no pertenece a este usuario");

        }

        tareaRepository.delete(tarea);

    }


    private Usuario getUsuario(Integer usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(
                () -> new ResourceNotFoundException("Usuario", "id", usuarioId.toString())
        );
        return usuario;
    }

}
