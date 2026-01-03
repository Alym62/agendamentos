package com.aly.miniagendamento.core.domain;

import com.aly.miniagendamento.core.enums.StatusAgendamento;

import java.time.LocalDateTime;

/**
 * Entidade de domínio da aplicação - Core
 * @param id
 * @param titulo
 * @param descricao
 * @param dataInicio
 * @param dataFim
 * @param status
 * @param usuario
 * @param criadoEm
 * @param atualizadoEm
 */
public record Agendamento(
        Long id,
        String titulo,
        String descricao,
        LocalDateTime dataInicio,
        LocalDateTime dataFim,
        StatusAgendamento status,
        String usuario,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm
) {
}
