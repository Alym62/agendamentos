package com.aly.miniagendamento.core.gateway;

import com.aly.miniagendamento.core.domain.Agendamento;

public interface AgendamentoGateway {
    Agendamento criarAgendamento(Agendamento agendamento);
    Agendamento atualizarAgendamentoPorId(Long idAgendamento, Agendamento agendamento);
    Agendamento cancelarAgendamentoPorId(Long idAgendamento);
    Agendamento getAgendamentoPorId(Long idAgendamento);
    Agendamento concluirAgendamento(Long idAgendamento);
}
