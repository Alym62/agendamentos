package com.aly.miniagendamento.service.impl;

import com.aly.miniagendamento.domain.Agendamento;
import com.aly.miniagendamento.domain.dto.requests.AtualizarAgendamentoRequest;
import com.aly.miniagendamento.domain.dto.requests.CriarAgendamentoRequest;
import com.aly.miniagendamento.domain.dto.responses.AgendamentoResponse;
import com.aly.miniagendamento.domain.enums.StatusAgendamento;
import com.aly.miniagendamento.exceptions.AgendamentoNaoEncontradoException;
import com.aly.miniagendamento.exceptions.ConflitoDeAgendamentoException;
import com.aly.miniagendamento.mapper.AgendamentoMapper;
import com.aly.miniagendamento.repository.AgendamentoRepository;
import com.aly.miniagendamento.service.IAgendamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AgendamentoService implements IAgendamentoService {
    private final AgendamentoRepository agendamentoRepository;

    @Transactional
    @Override
    public AgendamentoResponse criarAgendamento(CriarAgendamentoRequest dto) {
        validarDataInicioAndFim(dto.dataInicio(), dto.dataFim());

        checarConflitoDeAgendamento(dto.usuario(), dto.dataInicio(), dto.dataFim(), null);

        Agendamento domain = AgendamentoMapper.toDomain(dto);
        domain = agendamentoRepository.save(domain);

        return AgendamentoMapper.toResponse(domain);
    }

    @Transactional
    @Override
    public AgendamentoResponse atualizarAgendamentoPorId(Long idAgendamento, AtualizarAgendamentoRequest dto) {
        Agendamento domain = getAgendamentoPorId(idAgendamento);
        AgendamentoMapper.merge(domain, dto);

        validarDataInicioAndFim(domain.getDataInicio(), domain.getDataFim());
        checarConflitoDeAgendamento(domain.getUsuario(), domain.getDataInicio(), domain.getDataFim(), domain.getId());
        domain = agendamentoRepository.save(domain);
        return AgendamentoMapper.toResponse(domain);
    }

    @Transactional
    @Override
    public AgendamentoResponse cancelarAgendamentoPorId(Long idAgendamento) {
        Agendamento domain = atualizarStatusDoAgendamento(idAgendamento, StatusAgendamento.CANCELADO);
        return AgendamentoMapper.toResponse(domain);
    }

    @Override
    public Agendamento getAgendamentoPorId(Long idAgendamento) {
        return agendamentoRepository.findById(idAgendamento)
                .orElseThrow(() -> new AgendamentoNaoEncontradoException("Não foi possível buscar o agendamento nesse momento"));
    }

    @Transactional
    @Override
    public AgendamentoResponse concluirAgendamento(Long idAgendamento) {
        Agendamento domain = atualizarStatusDoAgendamento(idAgendamento, StatusAgendamento.CONCLUIDO);
        return AgendamentoMapper.toResponse(domain);
    }

    private Agendamento atualizarStatusDoAgendamento(Long idAgendamento, StatusAgendamento status) {
        Agendamento domain = getAgendamentoPorId(idAgendamento);

        if (status.equals(domain.getStatus())) {
            throw new ConflitoDeAgendamentoException("O agendamento já se encontra no status: " + status);
        }

        if (StatusAgendamento.CONCLUIDO.equals(domain.getStatus()) && status.equals(StatusAgendamento.CANCELADO)) {
            throw new ConflitoDeAgendamentoException("Não é possível cancelar um agendamento concluído.");
        }

        if (StatusAgendamento.CANCELADO.equals(domain.getStatus()) && status.equals(StatusAgendamento.CONCLUIDO)) {
            throw new ConflitoDeAgendamentoException("Não é possível concluir um agendamento cancelado.");
        }

        domain.setStatus(status);

        return agendamentoRepository.save(domain);
    }

    private void validarDataInicioAndFim(LocalDateTime dataInicio, LocalDateTime dataFim) {
        if (dataInicio == null || dataFim == null || !dataInicio.isBefore(dataFim)) {
            throw new ConflitoDeAgendamentoException("A data inicio precisa ser depois da data fim");
        }
    }

    private void checarConflitoDeAgendamento(String usuario, LocalDateTime inicio, LocalDateTime fim, Long id) {
        boolean conflitoDeAgendamento = agendamentoRepository.existsConflitoAoAgendar(usuario, inicio, fim, id);
        if (conflitoDeAgendamento)
            throw new ConflitoDeAgendamentoException("Não foi possível agendar um horário pois já está agendado nesse momento");
    }
}
