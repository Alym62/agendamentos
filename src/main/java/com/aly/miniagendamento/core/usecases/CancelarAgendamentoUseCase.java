package com.aly.miniagendamento.core.usecases;

import com.aly.miniagendamento.core.domain.Agendamento;

public interface CancelarAgendamentoUseCase {
    Agendamento execute(Long idAgendamento);
}
