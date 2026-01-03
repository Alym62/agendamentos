package com.aly.miniagendamento.core.usecases;

import com.aly.miniagendamento.core.domain.Agendamento;

public interface AtualizarAgendamentoUseCase {
    Agendamento execute(Long idAgendamento, Agendamento agendamento);
}
