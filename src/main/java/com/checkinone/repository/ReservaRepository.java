package com.checkinone.repository;

import com.checkinone.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    List<Reserva> findTop10ByOrderByDataCadastroDesc();

    List<Reserva> findByHospedeResponsavelIdOrderByDataCadastroDesc(Long hospedeId);
}
