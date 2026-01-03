package com.aly.miniagendamento.core.usecases.impl;

import com.aly.miniagendamento.core.domain.Agendamento;
import com.aly.miniagendamento.core.exceptions.AgendamentoNaoEncontradoException;
import com.aly.miniagendamento.core.gateway.AgendamentoGateway;
import com.aly.miniagendamento.core.usecases.BuscarAgendamentoByIdUseCase;

public class BuscarAgendamentoByIdUseCaseImpl implements BuscarAgendamentoByIdUseCase {
    private final AgendamentoGateway agendamentoGateway;

    public BuscarAgendamentoByIdUseCaseImpl(AgendamentoGateway agendamentoGateway) {
        this.agendamentoGateway = agendamentoGateway;
    }

    @Override
    public Agendamento execute(Long idAgendamento) {
        Agendamento domain = agendamentoGateway.getAgendamentoPorId(idAgendamento);
        if (domain == null) {
            throw new AgendamentoNaoEncontradoException("Agendamento não encontrado");
        }

        return domain;
    }
}
