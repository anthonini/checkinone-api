package com.checkinone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.checkinone.model.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

}
