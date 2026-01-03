package com.aly.miniagendamento.infrastructure.controller;

import com.aly.miniagendamento.core.domain.Agendamento;
import com.aly.miniagendamento.core.usecases.*;
import com.aly.miniagendamento.infrastructure.dto.requests.AtualizarAgendamentoRequest;
import com.aly.miniagendamento.infrastructure.dto.requests.CriarAgendamentoRequest;
import com.aly.miniagendamento.infrastructure.dto.response.AgendamentoResponse;
import com.aly.miniagendamento.infrastructure.mapper.AgendamentoResponseMapper;
import com.aly.miniagendamento.infrastructure.mapper.AtualizarAgendamentoMapper;
import com.aly.miniagendamento.infrastructure.mapper.CriarAgendamentoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("api/v1/agendamentos")
@RequiredArgsConstructor
public class AgendamentoController {
    private final CriarAgendamentoUseCase criarAgendamentoUseCase;
    private final BuscarAgendamentoByIdUseCase buscarAgendamentoByIdUseCase;
    private final AtualizarAgendamentoUseCase atualizarAgendamentoUseCase;
    private final ConcluirAgendamentoUseCase concluirAgendamentoUseCase;
    private final CancelarAgendamentoUseCase cancelarAgendamentoUseCase;

    private final AgendamentoResponseMapper agendamentoResponseMapper;
    private final AtualizarAgendamentoMapper atualizarAgendamentoMapper;
    private final CriarAgendamentoMapper criarAgendamentoMapper;

    @PostMapping("/criar")
    public ResponseEntity<AgendamentoResponse> criarAgendamento(@Valid @RequestBody CriarAgendamentoRequest request) {
        Agendamento requestMapeada = criarAgendamentoMapper.toCore(request);
        Agendamento agendamentoCriado = criarAgendamentoUseCase.execute(requestMapeada);

        AgendamentoResponse response = agendamentoResponseMapper.toDto(agendamentoCriado);
        return ResponseEntity.created(URI.create("/" + response.id())).body(response);
    }

    @PutMapping("/atualizar/{idAgendamento}")
    public ResponseEntity<AgendamentoResponse> atualizarAgendamento(@PathVariable Long idAgendamento,
                                                                    @Valid @RequestBody AtualizarAgendamentoRequest request) {
        Agendamento agendamentoExistente = buscarAgendamentoByIdUseCase.execute(idAgendamento);

        Agendamento agendamentoParaAtualizar = atualizarAgendamentoMapper.merge(agendamentoExistente, request);
        Agendamento agendamentoAtualizado = atualizarAgendamentoUseCase.execute(idAgendamento, agendamentoParaAtualizar);

        AgendamentoResponse response = agendamentoResponseMapper.toDto(agendamentoAtualizado);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/cancelar/{idAgendamento}")
    public ResponseEntity<AgendamentoResponse> cancelarAgendamento(@PathVariable Long idAgendamento) {
        Agendamento agendamentoCancelado = cancelarAgendamentoUseCase.execute(idAgendamento);
        AgendamentoResponse response = agendamentoResponseMapper.toDto(agendamentoCancelado);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/concluir/{idAgendamento}")
    public ResponseEntity<AgendamentoResponse> concluirAgendamento(@PathVariable Long idAgendamento) {
        Agendamento agendamentoConcluido = concluirAgendamentoUseCase.execute(idAgendamento);
        AgendamentoResponse response = agendamentoResponseMapper.toDto(agendamentoConcluido);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{idAgendamento}")
    public ResponseEntity<AgendamentoResponse> buscarPorId(@PathVariable Long idAgendamento) {
        Agendamento agendamentoExistente = buscarAgendamentoByIdUseCase.execute(idAgendamento);
        AgendamentoResponse response = agendamentoResponseMapper.toDto(agendamentoExistente);
        return ResponseEntity.ok().body(response);
    }
}
