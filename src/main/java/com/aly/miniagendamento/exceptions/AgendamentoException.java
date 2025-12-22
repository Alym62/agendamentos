package com.aly.miniagendamento.exceptions;

import org.springframework.http.HttpStatus;

public abstract class AgendamentoException extends RuntimeException {
    private final String mensagem;

    public AgendamentoException(String mensagem) {
        super(mensagem);
        this.mensagem = mensagem;
    }

    protected ErrorDetails toErrorDetails() {
        return ErrorDetails.builder()
                .titulo("Erro na comunicação com a API")
                .mensagem(mensagem)
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();
    }
}
