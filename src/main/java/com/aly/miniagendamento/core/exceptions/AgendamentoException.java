package com.aly.miniagendamento.core.exceptions;

public abstract class AgendamentoException extends RuntimeException {
    private final String mensagem;

    public AgendamentoException(String mensagem) {
        super(mensagem);
        this.mensagem = mensagem;
    }

    public ErrorDetails toErrorDetails() {
        return new ErrorDetails(
                "Erro na comunicação com a API",
                mensagem,
                500,
                null
        );
    }
}
