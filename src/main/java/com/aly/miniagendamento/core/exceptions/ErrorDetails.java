package com.aly.miniagendamento.core.exceptions;

import java.util.Map;

public record ErrorDetails(
        String titulo,
        String mensagem,
        int status,
        Map<String, String> parametrosInvalidos
) {
}
