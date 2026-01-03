package com.aly.miniagendamento.core.usecases.impl;

import com.aly.miniagendamento.core.domain.Agendamento;
import com.aly.miniagendamento.core.exceptions.AgendamentoNaoEncontradoException;
import com.aly.miniagendamento.core.gateway.AgendamentoGateway;
import com.aly.miniagendamento.core.usecases.AtualizarAgendamentoUseCase;

public class AtualizarAgendamentoUseCaseImpl implements AtualizarAgendamentoUseCase {
    private final AgendamentoGateway agendamentoGateway;

    public AtualizarAgendamentoUseCaseImpl(AgendamentoGateway agendamentoGateway) {
        this.agendamentoGateway = agendamentoGateway;
    }

    @Override
    public Agendamento execute(Long idAgendamento, Agendamento agendamento) {
        Agendamento domain = agendamentoGateway.getAgendamentoPorId(idAgendamento);
        if (domain == null) {
            throw new AgendamentoNaoEncontradoException("Agendamento não encontrado");
        }

        return agendamentoGateway.atualizarAgendamentoPorId(idAgendamento, agendamento);
    }
}
