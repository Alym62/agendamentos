package com.aly.miniagendamento.controller;

import com.aly.miniagendamento.domain.Agendamento;
import com.aly.miniagendamento.domain.dto.requests.AtualizarAgendamentoRequest;
import com.aly.miniagendamento.domain.dto.requests.CriarAgendamentoRequest;
import com.aly.miniagendamento.domain.dto.responses.AgendamentoResponse;
import com.aly.miniagendamento.service.IAgendamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("api/v1/agendamentos")
@RequiredArgsConstructor
public class AgendamentoController {
    private final IAgendamentoService agendamentoService;

    @PostMapping("/criar")
    public ResponseEntity<AgendamentoResponse> criarAgendamento(@Valid @RequestBody CriarAgendamentoRequest request) {
        AgendamentoResponse response = agendamentoService.criarAgendamento(request);
        return ResponseEntity.created(URI.create("/" + response.id())).body(response);
    }

    @PutMapping("/atualizar/{idAgendamento}")
    public ResponseEntity<AgendamentoResponse> atualizarAgendamento(@PathVariable Long idAgendamento,
                                                                    @Valid @RequestBody AtualizarAgendamentoRequest request) {
        AgendamentoResponse response = agendamentoService.atualizarAgendamentoPorId(idAgendamento, request);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/cancelar/{idAgendamento}")
    public ResponseEntity<AgendamentoResponse> cancelarAgendamento(@PathVariable Long idAgendamento) {
        AgendamentoResponse response = agendamentoService.cancelarAgendamentoPorId(idAgendamento);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/concluir/{idAgendamento}")
    public ResponseEntity<AgendamentoResponse> concluirAgendamento(@PathVariable Long idAgendamento) {
        AgendamentoResponse response = agendamentoService.concluirAgendamento(idAgendamento);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{idAgendamento}")
    public ResponseEntity<Agendamento> buscarPorId(@PathVariable Long idAgendamento) {
        Agendamento response = agendamentoService.getAgendamentoPorId(idAgendamento);
        return ResponseEntity.ok().body(response);
    }
}
