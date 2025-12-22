package com.aly.miniagendamento.repository;

import com.aly.miniagendamento.domain.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    @Query("""
            SELECT
                CASE
                    WHEN COUNT(A) > 0 THEN TRUE ELSE FALSE
                  END
            FROM
                Agendamento A
            WHERE
                A.usuario = :usuario
            AND
                A.status = 'AGENDADO'
            AND
                (A.dataInicio < :fim AND A.dataFim > :inicio)
            AND
                (:ignoraId IS NULL OR A.id <> :ignoraId)
            """)
    boolean existsConflitoAoAgendar(@Param("usuario") String usuario, @Param("inicio") LocalDateTime inicio,
                                    @Param("fim") LocalDateTime fim, @Param("ignoraId") Long ignoraId);
}
