package com.checkinone.model;

public enum StatusPagamento {

	PAGO("Pago"),
	PENDENTE("Pendente");
	
	private String descricao;
	
	private StatusPagamento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
