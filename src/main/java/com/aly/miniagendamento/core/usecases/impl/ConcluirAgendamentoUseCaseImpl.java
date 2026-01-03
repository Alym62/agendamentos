package com.aly.miniagendamento.core.usecases.impl;

import com.aly.miniagendamento.core.domain.Agendamento;
import com.aly.miniagendamento.core.exceptions.AgendamentoNaoEncontradoException;
import com.aly.miniagendamento.core.gateway.AgendamentoGateway;
import com.aly.miniagendamento.core.usecases.ConcluirAgendamentoUseCase;

public class ConcluirAgendamentoUseCaseImpl implements ConcluirAgendamentoUseCase {
    private final AgendamentoGateway agendamentoGateway;

    public ConcluirAgendamentoUseCaseImpl(AgendamentoGateway agendamentoGateway) {
        this.agendamentoGateway = agendamentoGateway;
    }

    @Override
    public Agendamento execute(Long idAgendamento) {
        Agendamento domain = agendamentoGateway.getAgendamentoPorId(idAgendamento);
        if (domain == null) {
            throw new AgendamentoNaoEncontradoException("Agendamento não encontrado");
        }

        return agendamentoGateway.concluirAgendamento(idAgendamento);
    }
}
