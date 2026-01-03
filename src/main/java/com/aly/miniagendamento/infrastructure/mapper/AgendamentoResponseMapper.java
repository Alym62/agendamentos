package com.aly.miniagendamento.infrastructure.mapper;

import com.aly.miniagendamento.core.domain.Agendamento;
import com.aly.miniagendamento.infrastructure.dto.response.AgendamentoResponse;
import org.springframework.stereotype.Component;

@Component
public class AgendamentoResponseMapper {
    public AgendamentoResponse toDto(Agendamento agendamento) {
        return AgendamentoResponse.builder()
                .id(agendamento.id())
                .titulo(agendamento.titulo())
                .descricao(agendamento.descricao())
                .dataInicio(agendamento.dataInicio())
                .dataFim(agendamento.dataFim())
                .status(agendamento.status())
                .usuario(agendamento.usuario())
                .criadoEm(agendamento.criadoEm())
                .atualizadoEm(agendamento.atualizadoEm())
                .build();
    }

    public Agendamento toCore(AgendamentoResponse dto) {
        return new Agendamento(
                dto.id(),
                dto.titulo(),
                dto.descricao(),
                dto.dataInicio(),
                dto.dataFim(),
                dto.status(),
                dto.usuario(),
                dto.criadoEm(),
                dto.atualizadoEm()
        );
    }
}
