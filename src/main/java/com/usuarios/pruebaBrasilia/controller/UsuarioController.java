package com.usuarios.pruebaBrasilia.controller;

import com.usuarios.pruebaBrasilia.dto.UsuarioRequestDTO;
import com.usuarios.pruebaBrasilia.dto.UsuarioResponseDTO;
import com.usuarios.pruebaBrasilia.services.UsuarioService;
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
@Tag(name = "Usuario", description = "Usuario API")
public class UsuarioController {

    private final UsuarioService usuarioService;


    @PostMapping()
    @Operation(summary = "Crear Nuevo Usuario", description = "Api para crear Usuario")
    public ResponseEntity<UsuarioResponseDTO> crear(@RequestBody @Valid UsuarioRequestDTO usuario) {

        UsuarioResponseDTO usuarioCreado = usuarioService.crear(usuario);

        URI uri = UriComponentsBuilder.newInstance()
                .path("/api/v1/usuario/{usuarioId}").buildAndExpand(usuarioCreado.getId()).toUri();
        return ResponseEntity.created(uri).body(usuarioCreado);

    }


    @GetMapping("/{usuarioId}")
    @Operation(summary = "Buscar Usuario", description = "Api para buscar Usuario por Id")
    public ResponseEntity<UsuarioResponseDTO> buscarUsuario(@PathVariable Integer usuarioId) {

        return ResponseEntity.ok(usuarioService.buscarUsuario(usuarioId));

    }


    @GetMapping()
    @Operation(summary = "Buscar todos los Usuarios", description = "Api para buscar todos los Usuarios")
    public ResponseEntity<List<UsuarioResponseDTO>> buscarTodosUsuario() {

        return ResponseEntity.ok(usuarioService.buscarTodos());

    }


}
