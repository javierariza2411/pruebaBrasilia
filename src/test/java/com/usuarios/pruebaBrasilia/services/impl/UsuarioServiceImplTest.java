package com.usuarios.pruebaBrasilia.services.impl;

import com.usuarios.pruebaBrasilia.dto.UsuarioRequestDTO;
import com.usuarios.pruebaBrasilia.dto.UsuarioResponseDTO;
import com.usuarios.pruebaBrasilia.entity.Usuario;
import com.usuarios.pruebaBrasilia.jpa.repository.UsuarioRepository;
import com.usuarios.pruebaBrasilia.services.UsuarioService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UsuarioServiceImplTest {


    @Mock
    UsuarioRepository usuarioRepository;

    @InjectMocks
    UsuarioServiceImpl service;

    @Test
    void crearUsuario(){

        Usuario usuario= Usuario.builder().
                id(1)
                .nombreUsuario("usuarioPrueba")
                .build();


        UsuarioRequestDTO usuarioDto= UsuarioRequestDTO.builder()
                .nombre("usuarioPrueba")
                .build();


        when(usuarioRepository.save(Mockito.any(Usuario.class))).thenReturn(usuario);

        UsuarioResponseDTO crear = service.crear(usuarioDto);

        assertEquals(1,crear.getId());


    }

}