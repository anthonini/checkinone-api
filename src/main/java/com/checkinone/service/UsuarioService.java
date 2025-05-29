package com.checkinone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.checkinone.api.dto.UsuarioDTO;
import com.checkinone.api.mapper.Mapper;
import com.checkinone.model.Usuario;
import com.checkinone.repository.UsuarioRepository;
import com.checkinone.service.exception.NegocioException;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private Mapper mapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	public UsuarioDTO cadastrar(@Valid UsuarioDTO usuarioDTO) throws NegocioException {
		Usuario usuario = mapper.map(usuarioDTO, Usuario.class);
		Optional<Usuario> usuarioExistentePorEmail = repository.findByEmail(usuario.getEmail());
		
		if(usuarioExistentePorEmail.isPresent() && !usuarioExistentePorEmail.get().equals(usuario)) {
			throw new NegocioException("E-mail já cadastrado");
		}
		
		usuario.setSenha(this.passwordEncoder.encode(usuario.getSenha()));
		
		return mapper.map(repository.save(usuario), UsuarioDTO.class);
	}
	
	public List<UsuarioDTO> buscarTodos() {
		List<Usuario> usuarios = repository.findAll();
		return mapper.mapList(usuarios, UsuarioDTO.class);
	}

	public Optional<UsuarioDTO> buscar(Long id) {
		return repository.findById(id).map(u -> mapper.map(u, UsuarioDTO.class));
	}
}
