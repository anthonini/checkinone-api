package com.checkinone.api.dto;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.NotBlank;

public class HospedeDTO {

    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @CPF
    @NotBlank(message = "CPF é obrigatório")
    private String cpf;

    @NotBlank(message = "Telefone é obrigatório")
    private String telefone;

    @NotBlank(message = "Endereço é obrigatório")
    private String endereco;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Nome é obrigatório") String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "Nome é obrigatório") String nome) {
        this.nome = nome;
    }

    public @NotBlank(message = "CPF é obrigatório") String getCpf() {
        return cpf;
    }

    public void setCpf(@NotBlank(message = "CPF é obrigatório") String cpf) {
        this.cpf = cpf;
    }

    public @NotBlank(message = "Telefone é obrigatório") String getTelefone() {
        return telefone;
    }

    public void setTelefone(@NotBlank(message = "Telefone é obrigatório") String telefone) {
        this.telefone = telefone;
    }

    public @NotBlank(message = "Endereço é obrigatório") String getEndereco() {
        return endereco;
    }

    public void setEndereco(@NotBlank(message = "Endereço é obrigatório") String endereco) {
        this.endereco = endereco;
    }
}
