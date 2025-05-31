package com.checkinone.repository;

import com.checkinone.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    List<Reserva> findTop10ByOrderByDataCadastroDesc();

    List<Reserva> findByHospedeIdOrderByDataCadastroDesc(Long hospedeId);

    @Query("SELECT COUNT(r) FROM Reserva r WHERE :hoje BETWEEN r.dataEntrada AND r.dataSaida")
    Long countReservasAtivasHoje(@Param("hoje") LocalDateTime hoje);
}
