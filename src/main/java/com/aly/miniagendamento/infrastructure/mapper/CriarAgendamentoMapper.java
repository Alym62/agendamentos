package com.aly.miniagendamento.infrastructure.mapper;

import com.aly.miniagendamento.core.domain.Agendamento;
import com.aly.miniagendamento.core.enums.StatusAgendamento;
import com.aly.miniagendamento.infrastructure.dto.requests.CriarAgendamentoRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CriarAgendamentoMapper {
    public CriarAgendamentoRequest toDto(Agendamento agendamento) {
        return new CriarAgendamentoRequest(
                agendamento.titulo(),
                agendamento.descricao(),
                agendamento.dataInicio(),
                agendamento.dataFim(),
                agendamento.usuario()
        );
    }

    public Agendamento toCore(CriarAgendamentoRequest dto) {
        return new Agendamento(
                null,
                dto.titulo(),
                dto.descricao(),
                dto.dataInicio(),
                dto.dataFim(),
                StatusAgendamento.AGENDADO,
                dto.usuario(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}
