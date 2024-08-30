package com.usuarios.pruebaBrasilia.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioRequestDTO {

    @NotBlank(message = "El nombre es requerido")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]*$", message = "Nombre no es Valido")
    private String nombre;
}
