package com.checkinone.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.checkinone.api.dto.FuncionarioDTO;
import com.checkinone.api.dto.FuncionarioDTOPost;
import com.checkinone.service.FuncionarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioService service;
	
	@PostMapping
	public ResponseEntity<FuncionarioDTOPost> cadastrar(@Valid @RequestBody FuncionarioDTOPost funcionario) {
		funcionario = service.cadastrar(funcionario);
		return ResponseEntity.status(HttpStatus.CREATED).body(funcionario);
	}
	
	@GetMapping
	public List<FuncionarioDTO> listar() {
		return service.listar();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<FuncionarioDTO> buscarPorId(@PathVariable Long id) {
		Optional<FuncionarioDTO> funcionario = service.buscar(id);		
		return funcionario.isPresent() ? ResponseEntity.ok(funcionario.get()) : ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<FuncionarioDTOPost> atualizar(@PathVariable Long id, @Valid @RequestBody FuncionarioDTOPost funcionario) {
		funcionario.setId(id);
		funcionario = service.atualizar(funcionario);
		return ResponseEntity.ok(funcionario);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.remover(id);
	}
}
