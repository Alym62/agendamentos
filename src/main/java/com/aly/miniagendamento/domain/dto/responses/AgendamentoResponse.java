package com.aly.miniagendamento.domain.dto.responses;

import com.aly.miniagendamento.domain.enums.StatusAgendamento;
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
