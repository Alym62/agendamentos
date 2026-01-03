package com.aly.miniagendamento.infrastructure.exceptions;

import com.aly.miniagendamento.core.exceptions.AgendamentoException;
import com.aly.miniagendamento.core.exceptions.AgendamentoNaoEncontradoException;
import com.aly.miniagendamento.core.exceptions.ConflitoDeAgendamentoException;
import com.aly.miniagendamento.core.exceptions.ErrorDetails;
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
                new ErrorDetails(
                        "Parametros inválidos",
                        "Os parametros passados ao agendar estão invalidos",
                        400,
                        invalidParams
                ),
                HttpStatus.BAD_REQUEST
        );
    }
}
