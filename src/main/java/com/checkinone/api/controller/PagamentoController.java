package com.checkinone.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.checkinone.api.dto.PagamentoDTO;
import com.checkinone.service.PagamentoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

	@Autowired
	private PagamentoService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<PagamentoDTO> buscarPorId(@PathVariable Long id) {
		Optional<PagamentoDTO> pagamento = service.buscar(id);		
		return pagamento.isPresent() ? ResponseEntity.ok(pagamento.get()) : ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PagamentoDTO> atualizar(@PathVariable Long id, @Valid @RequestBody PagamentoDTO pagamento) {
		pagamento.setId(id);
		pagamento = service.salvar(pagamento);
		return ResponseEntity.ok(pagamento);
	}
}
