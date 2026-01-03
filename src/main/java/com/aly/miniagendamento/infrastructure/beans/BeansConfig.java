package com.aly.miniagendamento.infrastructure.beans;

import com.aly.miniagendamento.core.gateway.AgendamentoGateway;
import com.aly.miniagendamento.core.usecases.*;
import com.aly.miniagendamento.core.usecases.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {
    @Bean
    public CriarAgendamentoUseCase criarAgendamentoUseCase(AgendamentoGateway agendamentoGateway) {
        return new CriarAgendamentoUseCaseImpl(agendamentoGateway);
    }

    @Bean
    public AtualizarAgendamentoUseCase atualizarAgendamentoUseCase(AgendamentoGateway agendamentoGateway) {
        return new AtualizarAgendamentoUseCaseImpl(agendamentoGateway);
    }

    @Bean
    public BuscarAgendamentoByIdUseCase buscarAgendamentoByIdUseCase(AgendamentoGateway agendamentoGateway) {
        return new BuscarAgendamentoByIdUseCaseImpl(agendamentoGateway);
    }

    @Bean
    public CancelarAgendamentoUseCase cancelarAgendamentoUseCase(AgendamentoGateway agendamentoGateway) {
        return new CancelarAgendamentoUseCaseImpl(agendamentoGateway);
    }

    @Bean
    public ConcluirAgendamentoUseCase concluirAgendamentoUseCase(AgendamentoGateway agendamentoGateway) {
        return new ConcluirAgendamentoUseCaseImpl(agendamentoGateway);
    }
}
