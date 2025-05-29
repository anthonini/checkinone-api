package com.checkinone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.checkinone.api.dto.FuncionarioDTO;
import com.checkinone.api.dto.FuncionarioDTOPost;
import com.checkinone.api.mapper.Mapper;
import com.checkinone.model.Funcionario;
import com.checkinone.repository.FuncionarioRepository;
import com.checkinone.service.exception.NegocioException;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository repository;
	
	@Autowired
	private Mapper mapper;
	
	@Autowired
	private MessageSource messageSource;
	
	@Transactional
	public FuncionarioDTOPost cadastrar(FuncionarioDTOPost funcionarioDTO) {
		Funcionario funcionario = mapper.map(funcionarioDTO, Funcionario.class);
		return mapper.map(repository.save(funcionario), FuncionarioDTOPost.class);
	}

	public List<FuncionarioDTO> listar() {
		List<Funcionario> funcionarios = repository.findAll();
		return mapper.mapList(funcionarios, FuncionarioDTO.class);
	}

	public Optional<FuncionarioDTO> buscar(Long id) {
		return repository.findById(id).map(u -> mapper.map(u, FuncionarioDTO.class));
	}
	
	@Transactional
	public FuncionarioDTOPost atualizar(@Valid FuncionarioDTOPost funcionarioDTO) {
		Optional<Funcionario> funcionarioCadastrado = repository.findById(funcionarioDTO.getId());
		if(funcionarioCadastrado.isEmpty()) {
			throw new NegocioException(messageSource.getMessage("funcionario.nao-encontrado", null, LocaleContextHolder.getLocale()));
		}
		
		Funcionario funcionario = mapper.map(funcionarioDTO, Funcionario.class);
		return mapper.map(repository.save(funcionario), FuncionarioDTOPost.class);
	}

	@Transactional
	public void remover(Long id) {
		repository.deleteById(id);
	}

}
