package com.aly.miniagendamento.core.exceptions;

public class AgendamentoNaoEncontradoException extends AgendamentoException {
    private final String mensagem;

    public AgendamentoNaoEncontradoException(String mensagem) {
        super(mensagem);
        this.mensagem = mensagem;
    }

    @Override
    public ErrorDetails toErrorDetails() {
        return new ErrorDetails(
                "Agendamento não encontrado",
                mensagem,
                400,
                null
        );
    }
}
