package com.aly.miniagendamento.exceptions;

import org.springframework.http.HttpStatus;

public class ConflitoDeAgendamentoException extends AgendamentoException{
    private final String mensagem;

    public ConflitoDeAgendamentoException(String mensagem) {
        super(mensagem);
        this.mensagem = mensagem;
    }

    @Override
    protected ErrorDetails toErrorDetails() {
        return ErrorDetails.builder()
                .titulo("Conflito de agendamento")
                .mensagem(mensagem)
                .status(HttpStatus.BAD_REQUEST.value())
                .build();
    }
}
