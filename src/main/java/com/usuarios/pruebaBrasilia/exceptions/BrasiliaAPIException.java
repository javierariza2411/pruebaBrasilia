package com.usuarios.pruebaBrasilia.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class BrasiliaAPIException extends RuntimeException {
    private HttpStatus status;
    private String message;

    public BrasiliaAPIException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

}