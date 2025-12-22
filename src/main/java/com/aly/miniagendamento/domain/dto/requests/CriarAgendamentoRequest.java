package com.aly.miniagendamento.domain.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record CriarAgendamentoRequest(
        @NotBlank(message = "O titulo precisa ser preenchido")
        @Size(max = 120, message = "O titulo precisa ter no máximo 120 caracteres")
        String titulo,
        @Size(max = 4000, message = "A descrição precisa ter no máximo 4000 caracteres")
        String descricao,
        @NotNull(message = "A data inicio precisa ser preenchido")
        LocalDateTime dataInicio,
        @NotNull(message = "A data fim precisa ser preenchido")
        LocalDateTime dataFim,
        @NotBlank(message = "O usuário precisa ser preenchido")
        @Size(max = 80, message = "O usuário pode ter no máximo 80 caracteres")
        String usuario
) {
}
