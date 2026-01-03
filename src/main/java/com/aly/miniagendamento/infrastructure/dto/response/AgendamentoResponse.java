package com.aly.miniagendamento.infrastructure.dto.response;

import com.aly.miniagendamento.core.enums.StatusAgendamento;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record AgendamentoResponse(
        Long id,
        String titulo,
        String descricao,
        LocalDateTime dataInicio,
        LocalDateTime dataFim,
        String usuario,
        StatusAgendamento status,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm
) {
}
