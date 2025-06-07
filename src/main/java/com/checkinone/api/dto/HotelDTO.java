package com.checkinone.api.dto;

import com.checkinone.model.Funcionario;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class HotelDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "Nome é campo obrigatório")
    private String nome;
    
    @NotBlank(message = "Endereço é campo obrigatório")
    private String endereco;

    @NotBlank(message = "Telefone é campo obrigatório")
    private String telefone;

    @NotNull(message = "Gerente é campo obrigatório")
    private Funcionario gerente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Nome é campo obrigatório") String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "Nome é campo obrigatório") String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public @NotBlank(message = "Telefone é campo obrigatório") String getTelefone() {
        return telefone;
    }

    public void setTelefone(@NotBlank(message = "Telefone é campo obrigatório") String telefone) {
        this.telefone = telefone;
    }

    public @NotNull(message = "Gerente é campo obrigatório") Funcionario getGerente() {
        return gerente;
    }

    public void setGerente(@NotNull(message = "Gerente é campo obrigatório") Funcionario gerente) {
        this.gerente = gerente;
    }
}
