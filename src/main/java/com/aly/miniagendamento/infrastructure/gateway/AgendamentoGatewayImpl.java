package com.aly.miniagendamento.infrastructure.gateway;

import com.aly.miniagendamento.core.domain.Agendamento;
import com.aly.miniagendamento.core.enums.StatusAgendamento;
import com.aly.miniagendamento.core.exceptions.AgendamentoNaoEncontradoException;
import com.aly.miniagendamento.core.exceptions.ConflitoDeAgendamentoException;
import com.aly.miniagendamento.core.gateway.AgendamentoGateway;
import com.aly.miniagendamento.infrastructure.mapper.AgendamentoPersistenceMapper;
import com.aly.miniagendamento.infrastructure.persistence.AgendamentoPersistence;
import com.aly.miniagendamento.infrastructure.repository.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class AgendamentoGatewayImpl implements AgendamentoGateway {
    private final AgendamentoRepository agendamentoRepository;
    private final AgendamentoPersistenceMapper agendamentoPersistenceMapper;

    @Transactional
    @Override
    public Agendamento criarAgendamento(Agendamento agendamento) {
        validarDataInicioAndFim(agendamento.dataInicio(), agendamento.dataFim());

        checarConflitoDeAgendamento(agendamento.usuario(), agendamento.dataInicio(), agendamento.dataFim(), null);

        AgendamentoPersistence domain = agendamentoPersistenceMapper.toPersistence(agendamento);
        domain = agendamentoRepository.save(domain);

        return agendamentoPersistenceMapper.toCore(domain);
    }

    @Transactional
    @Override
    public Agendamento atualizarAgendamentoPorId(Long idAgendamento, Agendamento agendamento) {
        AgendamentoPersistence agendamentoPersistence = agendamentoPersistenceMapper.toPersistence(agendamento);

        validarDataInicioAndFim(agendamentoPersistence.getDataInicio(), agendamentoPersistence.getDataFim());
        checarConflitoDeAgendamento(agendamentoPersistence.getUsuario(), agendamentoPersistence.getDataInicio(), agendamentoPersistence.getDataFim(), agendamentoPersistence.getId());

        agendamentoPersistence = agendamentoRepository.save(agendamentoPersistence);

        return agendamentoPersistenceMapper.toCore(agendamentoPersistence);
    }

    @Transactional
    @Override
    public Agendamento cancelarAgendamentoPorId(Long idAgendamento) {
        AgendamentoPersistence agendamentoAtualizadoComStatusCancelado = atualizarStatusDoAgendamento(idAgendamento, StatusAgendamento.CANCELADO);
        return agendamentoPersistenceMapper.toCore(agendamentoAtualizadoComStatusCancelado);
    }

    @Override
    public Agendamento getAgendamentoPorId(Long idAgendamento) {
        AgendamentoPersistence agendamentoExistente = agendamentoRepository.findById(idAgendamento)
                .orElseThrow(() -> new AgendamentoNaoEncontradoException("Não foi possível buscar o agendamento nesse momento"));
        return agendamentoPersistenceMapper.toCore(agendamentoExistente);
    }

    @Transactional
    @Override
    public Agendamento concluirAgendamento(Long idAgendamento) {
        AgendamentoPersistence agendamentoAtualizadoComStatusConcluido = atualizarStatusDoAgendamento(idAgendamento, StatusAgendamento.CONCLUIDO);
        return agendamentoPersistenceMapper.toCore(agendamentoAtualizadoComStatusConcluido);
    }

    private AgendamentoPersistence atualizarStatusDoAgendamento(Long idAgendamento, StatusAgendamento status) {
        AgendamentoPersistence agendamentoExistente = agendamentoRepository.findById(idAgendamento)
                .orElseThrow(() -> new AgendamentoNaoEncontradoException("Não foi possível buscar o agendamento nesse momento"));

        if (status.equals(agendamentoExistente.getStatus())) {
            throw new ConflitoDeAgendamentoException("O agendamento já se encontra no status: " + status);
        }

        if (StatusAgendamento.CONCLUIDO.equals(agendamentoExistente.getStatus()) && status.equals(StatusAgendamento.CANCELADO)) {
            throw new ConflitoDeAgendamentoException("Não é possível cancelar um agendamento concluído.");
        }

        if (StatusAgendamento.CANCELADO.equals(agendamentoExistente.getStatus()) && status.equals(StatusAgendamento.CONCLUIDO)) {
            throw new ConflitoDeAgendamentoException("Não é possível concluir um agendamento cancelado.");
        }

        agendamentoExistente.setStatus(status);

        return agendamentoRepository.save(agendamentoExistente);
    }

    private void validarDataInicioAndFim(LocalDateTime dataInicio, LocalDateTime dataFim) {
        if (dataInicio == null || dataFim == null || !dataInicio.isBefore(dataFim)) {
            throw new ConflitoDeAgendamentoException("A data fim precisa ser depois da data início");
        }
    }

    private void checarConflitoDeAgendamento(String usuario, LocalDateTime inicio, LocalDateTime fim, Long id) {
        boolean conflitoDeAgendamento = agendamentoRepository.existsConflitoAoAgendar(usuario, inicio, fim, id);
        if (conflitoDeAgendamento)
            throw new ConflitoDeAgendamentoException("Não foi possível agendar um horário pois já está agendado nesse momento");
    }
}
