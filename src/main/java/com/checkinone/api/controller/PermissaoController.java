package com.checkinone.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.checkinone.api.dto.PermissaoDTO;
import com.checkinone.service.PermissaoService;

@RestController
@RequestMapping("/permissoes")
public class PermissaoController {

	@Autowired
	private PermissaoService service;
	
	@GetMapping
	public List<PermissaoDTO> listar() {
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PermissaoDTO> buscarPorId(@PathVariable Long id) {
		Optional<PermissaoDTO> permissao = service.findById(id);		
		return permissao.isPresent() ? ResponseEntity.ok(permissao.get()) : ResponseEntity.notFound().build();
	}
	
}
