package com.checkinone.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checkinone.api.dto.PagamentoDTO;
import com.checkinone.api.mapper.Mapper;
import com.checkinone.model.FormaPagamento;
import com.checkinone.model.Pagamento;
import com.checkinone.model.StatusPagamento;
import com.checkinone.repository.PagamentoRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class PagamentoService {

	@Autowired
	private PagamentoRepository repository;
	
	@Autowired
	private Mapper mapper;

	public Optional<PagamentoDTO> buscar(Long id) {
		return repository.findById(id).map(u -> mapper.map(u, PagamentoDTO.class));
	}
	
	@Transactional
	public PagamentoDTO salvar(@Valid PagamentoDTO pagamentoDTO) {
		Pagamento pagamento = mapper.map(pagamentoDTO, Pagamento.class);
		if(pagamento.getStatus() == null) {
			pagamento.setStatus(StatusPagamento.PENDENTE);
		}
		if(pagamento.getFormaPagamento().equals(FormaPagamento.CARTAO) && !pagamento.getStatus().equals(StatusPagamento.PAGO)) {
			pagamento.setStatus(StatusPagamento.PROCESSANDO);
		}
		
		if(pagamento.getStatus().equals(StatusPagamento.PAGO) && pagamento.getData() == null) {
			pagamento.setData(LocalDateTime.now());
		}
		
		
		return mapper.map(repository.save(pagamento), PagamentoDTO.class);
	}
}
