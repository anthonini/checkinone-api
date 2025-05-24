package com.checkinone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checkinone.api.dto.PermissaoDTO;
import com.checkinone.api.mapper.Mapper;
import com.checkinone.model.Permissao;
import com.checkinone.repository.PermissaoRepository;

@Service
public class PermissaoService {
	
	@Autowired
	private PermissaoRepository repository;
	
	@Autowired
	private Mapper mapper;
	
	public List<PermissaoDTO> findAll() {
		List<Permissao> permissoes = repository.findAll();
		return mapper.mapList(permissoes, PermissaoDTO.class);
	}

	public Optional<PermissaoDTO> findById(Long id) {
		return repository.findById(id).map(u -> mapper.map(u, PermissaoDTO.class));
	}
}
