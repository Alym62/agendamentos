package com.aly.miniagendamento.infrastructure.persistence;

import com.aly.miniagendamento.core.enums.StatusAgendamento;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_AGENDAMENTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgendamentoPersistence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITULO", nullable = false, length = 120)
    private String titulo;

    @Column(name = "DESCRICAO", columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "DATAINICIO", nullable = false)
    private LocalDateTime dataInicio;

    @Column(name = "DATAFIM", nullable = false)
    private LocalDateTime dataFim;

    @Column(name = "STATUS", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private StatusAgendamento status;

    @Column(name = "USUARIO", nullable = false, length = 80)
    private String usuario;

    @Column(name = "CRIADOEM", nullable = false)
    private LocalDateTime criadoEm;

    @Column(name = "ATUALIZADOEM", nullable = false)
    private LocalDateTime atualizadoEm;
}
