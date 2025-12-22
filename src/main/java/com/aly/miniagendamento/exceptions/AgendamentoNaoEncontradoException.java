package com.aly.miniagendamento.exceptions;

import org.springframework.http.HttpStatus;

public class AgendamentoNaoEncontradoException extends AgendamentoException {
    private final String mensagem;

    public AgendamentoNaoEncontradoException(String mensagem) {
        super(mensagem);
        this.mensagem = mensagem;
    }

    @Override
    protected ErrorDetails toErrorDetails() {
        return ErrorDetails.builder()
                .titulo("Agendamento não encontrado")
                .mensagem(mensagem)
                .status(HttpStatus.NOT_FOUND.value())
                .build();
    }
}
