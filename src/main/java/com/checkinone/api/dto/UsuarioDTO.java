package com.checkinone.api.dto;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UsuarioDTO {
	
	private Long id;

	@NotBlank(message = "Nome é obrigatório")
	private String nome;
	
	@Email(message = "E-mail inválido")
	@NotBlank(message = "E-mail é obrigatório")
	private String email;
	
	@NotBlank(message = "Senha é obrigatório")
	private String senha;
	
	private List<PermissaoDTO> permissoes = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<PermissaoDTO> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<PermissaoDTO> permissoes) {
		this.permissoes = permissoes;
	}
}
