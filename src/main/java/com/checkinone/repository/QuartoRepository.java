package com.checkinone.repository;

import com.checkinone.model.Quarto;
import com.checkinone.model.StatusOcupacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuartoRepository extends JpaRepository<Quarto, Long> {

    Long countByStatus(StatusOcupacao statusOcupacao);
}
