package com.aly.miniagendamento.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(AgendamentoException.class)
    public ResponseEntity<ErrorDetails> agendamentoException(AgendamentoException exception) {
        return new ResponseEntity<>(
                exception.toErrorDetails(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(AgendamentoNaoEncontradoException.class)
    public ResponseEntity<ErrorDetails> agendamentoNaoEncontradoException(AgendamentoNaoEncontradoException exception) {
        return new ResponseEntity<>(
                exception.toErrorDetails(),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(ConflitoDeAgendamentoException.class)
    public ResponseEntity<ErrorDetails> conflitoDeAgendamentoException(ConflitoDeAgendamentoException exception) {
        return new ResponseEntity<>(
                exception.toErrorDetails(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> invalidParams = new HashMap<>();

        for (FieldError fieldError : exception.getFieldErrors()) {
            invalidParams.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return new ResponseEntity<>(
                ErrorDetails.builder()
                        .titulo("Parametros inválidos")
                        .mensagem("Os parametros passados ao agendar estão invalidos")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .parametrosInvalidos(invalidParams)
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }
}
