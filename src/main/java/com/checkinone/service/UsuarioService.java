package com.checkinone.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checkinone.api.dto.UsuarioDTO;
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
	private ModelMapper mapper;

	public Optional<UsuarioDTO> findById(Long id) {
		return repository.findById(id).map(u -> mapper.map(u, UsuarioDTO.class));
	}

	@Transactional
	public UsuarioDTO cadastrar(@Valid UsuarioDTO usuarioDTO) throws NegocioException {
		Usuario usuario = mapper.map(usuarioDTO, Usuario.class);
		Optional<Usuario> usuarioExistentePorEmail = repository.findByEmail(usuario.getEmail());
		
		if(usuarioExistentePorEmail.isPresent()) {
			throw new NegocioException("E-mail já cadastrado");
		}
		
		return mapper.map(repository.save(usuario), UsuarioDTO.class);
	}
	
}
