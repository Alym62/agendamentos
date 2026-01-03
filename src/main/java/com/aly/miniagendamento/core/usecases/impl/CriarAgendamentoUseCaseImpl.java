package com.aly.miniagendamento.core.usecases.impl;

import com.aly.miniagendamento.core.domain.Agendamento;
import com.aly.miniagendamento.core.gateway.AgendamentoGateway;
import com.aly.miniagendamento.core.usecases.CriarAgendamentoUseCase;

public class CriarAgendamentoUseCaseImpl implements CriarAgendamentoUseCase {
    private final AgendamentoGateway agendamentoGateway;

    public CriarAgendamentoUseCaseImpl(AgendamentoGateway agendamentoGateway) {
        this.agendamentoGateway = agendamentoGateway;
    }

    @Override
    public Agendamento execute(Agendamento agendamento) {
        return agendamentoGateway.criarAgendamento(agendamento);
    }
}
