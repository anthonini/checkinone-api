package com.checkinone.model;

public enum StatusOcupacao {

    DISPONIVEL("Disponível"),
    OCUPADO("Ocupado"),
    MANUTENCAO("Manutenção");

    private String descricao;

    private StatusOcupacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
