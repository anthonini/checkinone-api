package com.checkinone.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.checkinone.api.dto.UsuarioDTO;
import com.checkinone.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class UsuarioController {

	@Autowired
	private UsuarioService service;
	
	@PostMapping
	public ResponseEntity<UsuarioDTO> cadastrar(@Valid @RequestBody UsuarioDTO usuario) {
		usuario = service.cadastrar(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
	}
	
	@GetMapping
	public List<UsuarioDTO> listar() {
		return service.buscarTodos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> buscar(@PathVariable Long id) {
		Optional<UsuarioDTO> usuario = service.buscar(id);		
		return usuario.isPresent() ? ResponseEntity.ok(usuario.get()) : ResponseEntity.notFound().build();
	}
}
