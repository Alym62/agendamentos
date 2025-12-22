package com.aly.miniagendamento.mapper;

import com.aly.miniagendamento.domain.Agendamento;
import com.aly.miniagendamento.domain.dto.requests.AtualizarAgendamentoRequest;
import com.aly.miniagendamento.domain.dto.requests.CriarAgendamentoRequest;
import com.aly.miniagendamento.domain.dto.responses.AgendamentoResponse;
import com.aly.miniagendamento.domain.enums.StatusAgendamento;

import java.time.LocalDateTime;

public class AgendamentoMapper {
    private AgendamentoMapper() {}

    public static Agendamento toDomain(CriarAgendamentoRequest dto) {
        return Agendamento.builder()
                .titulo(dto.titulo())
                .descricao(dto.descricao())
                .dataInicio(dto.dataInicio())
                .dataFim(dto.dataFim())
                .usuario(dto.usuario())
                .status(StatusAgendamento.AGENDADO)
                .criadoEm(LocalDateTime.now())
                .atualizadoEm(LocalDateTime.now())
                .build();
    }

    public static void merge(Agendamento domain, AtualizarAgendamentoRequest dto) {
        if (dto.titulo() != null) {
            domain.setTitulo(dto.titulo());
        }
        if (dto.descricao() != null) {
            domain.setDescricao(dto.descricao());
        }
        if (dto.dataInicio() != null) {
            domain.setDataInicio(dto.dataInicio());
        }
        if (dto.dataFim() != null) {
            domain.setDataFim(dto.dataFim());
        }
    }

    public static AgendamentoResponse toResponse(Agendamento domain) {
        return AgendamentoResponse.builder()
                .id(domain.getId())
                .titulo(domain.getTitulo())
                .descricao(domain.getDescricao())
                .dataInicio(domain.getDataInicio())
                .dataFim(domain.getDataFim())
                .usuario(domain.getUsuario())
                .status(domain.getStatus())
                .criadoEm(domain.getCriadoEm())
                .atualizadoEm(domain.getAtualizadoEm())
                .build();
    }
}
