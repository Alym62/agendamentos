package com.aly.miniagendamento.infrastructure.mapper;

import com.aly.miniagendamento.core.domain.Agendamento;
import com.aly.miniagendamento.core.enums.StatusAgendamento;
import com.aly.miniagendamento.infrastructure.dto.requests.AtualizarAgendamentoRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AtualizarAgendamentoMapper {
    public AtualizarAgendamentoRequest toDto(Agendamento agendamento) {
        return new AtualizarAgendamentoRequest(
                agendamento.titulo(),
                agendamento.descricao(),
                agendamento.dataInicio(),
                agendamento.dataFim()
        );
    }

    public Agendamento merge(Agendamento agendamentoExistente, AtualizarAgendamentoRequest dto) {
        return new Agendamento(
                agendamentoExistente.id(),
                dto.titulo() != null ? dto.titulo() : agendamentoExistente.titulo(),
                dto.descricao() != null ? dto.descricao() : agendamentoExistente.descricao(),
                dto.dataInicio() != null ? dto.dataInicio() : agendamentoExistente.dataInicio(),
                dto.dataFim() != null ? dto.dataFim() : agendamentoExistente.dataFim(),
                agendamentoExistente.status(),
                agendamentoExistente.usuario(),
                agendamentoExistente.criadoEm(),
                agendamentoExistente.atualizadoEm()
        );
    }

    public Agendamento toCore(AtualizarAgendamentoRequest dto) {
        return new Agendamento(
                null,
                dto.titulo(),
                dto.descricao(),
                dto.dataInicio(),
                dto.dataFim(),
                StatusAgendamento.AGENDADO,
                null,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}
