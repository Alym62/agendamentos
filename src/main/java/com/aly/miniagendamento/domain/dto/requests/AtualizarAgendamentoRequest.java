package com.aly.miniagendamento.domain.dto.requests;

import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record AtualizarAgendamentoRequest(
        @Size(max = 120, message = "O titulo precisa ter no máximo 120 caracteres") String titulo,
        @Size(max = 4000, message = "A descrição precisa ter no máximo 4000 caracteres") String descricao,
        LocalDateTime dataInicio,
        LocalDateTime dataFim
) {
}
