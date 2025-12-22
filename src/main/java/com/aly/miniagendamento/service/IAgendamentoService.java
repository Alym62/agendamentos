package com.aly.miniagendamento.service;

import com.aly.miniagendamento.domain.Agendamento;
import com.aly.miniagendamento.domain.dto.requests.AtualizarAgendamentoRequest;
import com.aly.miniagendamento.domain.dto.requests.CriarAgendamentoRequest;
import com.aly.miniagendamento.domain.dto.responses.AgendamentoResponse;

public interface IAgendamentoService {
    AgendamentoResponse criarAgendamento(CriarAgendamentoRequest dto);
    AgendamentoResponse atualizarAgendamentoPorId(Long idAgendamento, AtualizarAgendamentoRequest dto);
    AgendamentoResponse cancelarAgendamentoPorId(Long idAgendamento);
    Agendamento getAgendamentoPorId(Long idAgendamento);
    AgendamentoResponse concluirAgendamento(Long idAgendamento);
}
