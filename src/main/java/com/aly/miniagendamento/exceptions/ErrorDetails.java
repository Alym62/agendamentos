package com.aly.miniagendamento.exceptions;

import lombok.Builder;

import java.util.Map;

@Builder
public record ErrorDetails(
        String titulo,
        String mensagem,
        int status,
        Map<String, String> parametrosInvalidos
) {
}
