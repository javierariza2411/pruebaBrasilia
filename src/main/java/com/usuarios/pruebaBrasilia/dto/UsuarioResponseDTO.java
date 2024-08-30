package com.usuarios.pruebaBrasilia.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioResponseDTO {

    private Integer id;
    private String nombre;
}
