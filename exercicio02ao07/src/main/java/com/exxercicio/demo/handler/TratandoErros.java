package com.exxercicio.demo.handler;

import com.exxercicio.demo.exceptions.PlacaJaExiste;
import com.exxercicio.demo.exceptions.PlacaNaoExiste;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratandoErros {

    @ExceptionHandler(PlacaJaExiste.class)
    public ResponseEntity<String> trataPlacaJaExiste() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Desculpe, essa placa já existe.");
    }

    @ExceptionHandler(PlacaNaoExiste.class)
    public ResponseEntity<String> trataPlacaNaoExiste() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Desculpe, essa placa não existe.");
    }
}
