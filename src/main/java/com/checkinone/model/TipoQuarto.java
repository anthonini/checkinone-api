package com.checkinone.model;

public enum TipoQuarto {

    STANDARD("Standard"),
    SUITE("Suíte"),
    LUXO("Luxo");

    private String descricao;

    private TipoQuarto(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
