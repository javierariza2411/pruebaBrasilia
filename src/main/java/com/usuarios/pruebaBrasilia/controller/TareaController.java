package com.usuarios.pruebaBrasilia.controller;

import com.usuarios.pruebaBrasilia.dto.TareaRequestDTO;
import com.usuarios.pruebaBrasilia.dto.TareaResponseDTO;
import com.usuarios.pruebaBrasilia.dto.UsuarioRequestDTO;
import com.usuarios.pruebaBrasilia.dto.UsuarioResponseDTO;
import com.usuarios.pruebaBrasilia.services.TareaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/usuario")
@Tag(name = "Tarea", description = "Tarea API")
public class TareaController {


    private final TareaService tareaService;


    @PostMapping("/{usuarioId}/tarea")
    @Operation(summary = "Crear Nueva Tarea", description = "Api para Crear una Tarea a un Usuario Especifico")
    public ResponseEntity<TareaResponseDTO> crear(@PathVariable Integer usuarioId, @RequestBody @Valid TareaRequestDTO tarea) {

        TareaResponseDTO tareaCreada = tareaService.crear(usuarioId, tarea);

        URI uri = UriComponentsBuilder.newInstance()
                .path("/api/v1/usuario/{usuarioId}/tarea/{tareaId}").buildAndExpand(usuarioId, tareaCreada.getId()).toUri();

        return ResponseEntity.created(uri).body(tareaCreada);

    }


    @PutMapping("/{usuarioId}/tarea/{tareaId}")
    @Operation(summary = "Cambiar estado a una Tarea", description = "Api para Cambiar estado de una Tarea")
    public ResponseEntity<TareaResponseDTO> cambiarEstado(@PathVariable Integer usuarioId, @PathVariable Integer tareaId) {

        return ResponseEntity.ok(tareaService.cambiarEstado(usuarioId, tareaId));

    }


    @GetMapping("/{usuarioId}/tarea")
    @Operation(summary = "Buscar Tareas", description = "Api para Buscar las Tareas de Usuario")
    public ResponseEntity<List<TareaResponseDTO>> listarTodas(@PathVariable Integer usuarioId) {

        return ResponseEntity.ok(tareaService.buscarTareas(usuarioId));

    }


    @DeleteMapping("/{usuarioId}/tarea/{tareaId}")
    @Operation(summary = "Eliminar Tareas", description = "Api para Eliminar una Tarea de un Usuario")
    public ResponseEntity<String> eliminar(@PathVariable Integer usuarioId, @PathVariable Integer tareaId) {

        tareaService.eliminar(usuarioId, tareaId);
        return ResponseEntity.ok("se ha eliminado la tarea correctamente");

    }


}