package com.checkinone.model;

public enum StatusReserva {

    ATIVA("Pago"),
    CANCELADA("Pendente"),
    CONCLUIDA("Concluida");
    private String descricao;

    private StatusReserva(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
