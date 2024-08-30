package com.usuarios.pruebaBrasilia.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TareaResponseDTO {


    private Integer id;

    private String titulo;

    private String descripcion;

    private String estado;

}
