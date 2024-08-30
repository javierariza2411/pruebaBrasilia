package com.usuarios.pruebaBrasilia.services;

import com.usuarios.pruebaBrasilia.dto.TareaRequestDTO;
import com.usuarios.pruebaBrasilia.dto.TareaResponseDTO;

import java.util.List;

public interface TareaService {

   TareaResponseDTO crear(Integer usuarioId, TareaRequestDTO tarea);
   TareaResponseDTO cambiarEstado(Integer usuarioId, Integer tareaId);

   List<TareaResponseDTO> buscarTareas(Integer usuarioId);

    void eliminar(Integer usuarioId, Integer tareaId);



}
