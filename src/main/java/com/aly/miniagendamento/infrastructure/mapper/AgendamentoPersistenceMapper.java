package com.aly.miniagendamento.infrastructure.mapper;

import com.aly.miniagendamento.core.domain.Agendamento;
import com.aly.miniagendamento.core.enums.StatusAgendamento;
import com.aly.miniagendamento.infrastructure.persistence.AgendamentoPersistence;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AgendamentoPersistenceMapper {
    public AgendamentoPersistence toPersistence(Agendamento agendamento) {
        return AgendamentoPersistence.builder()
                .id(agendamento.id())
                .titulo(agendamento.titulo())
                .descricao(agendamento.descricao())
                .dataInicio(agendamento.dataInicio())
                .dataFim(agendamento.dataFim())
                .usuario(agendamento.usuario())
                .status(StatusAgendamento.AGENDADO)
                .criadoEm(LocalDateTime.now())
                .atualizadoEm(LocalDateTime.now())
                .build();
    }

    public Agendamento toCore(AgendamentoPersistence persistence) {
        return new Agendamento(
                persistence.getId(),
                persistence.getTitulo(),
                persistence.getDescricao(),
                persistence.getDataInicio(),
                persistence.getDataFim(),
                persistence.getStatus(),
                persistence.getUsuario(),
                persistence.getCriadoEm(),
                persistence.getAtualizadoEm()
        );
    }
}
