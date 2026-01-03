package com.aly.miniagendamento.core.exceptions;

public class ConflitoDeAgendamentoException extends AgendamentoException{
    private final String mensagem;

    public ConflitoDeAgendamentoException(String mensagem) {
        super(mensagem);
        this.mensagem = mensagem;
    }

    @Override
    public ErrorDetails toErrorDetails() {
        return new ErrorDetails(
                "Conflito de agendamento",
                mensagem,
                400,
                null
        );
    }
}
