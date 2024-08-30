package com.usuarios.pruebaBrasilia.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class TareaRequestDTO {

    @NotBlank(message = "El titulo es requerido")
    @Pattern(regexp = "^[a-zA-Z0-9 ,._-]*$", message = "Titulo no valido")
    private String titulo;

    @NotBlank(message = "La descripcion es requerido")
    @Pattern(regexp = "^[a-zA-Z0-9 ,._-]*$", message = "Descripcion no valida")
    private String descripcion;

}
