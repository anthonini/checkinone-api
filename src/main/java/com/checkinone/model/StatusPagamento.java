package com.checkinone.model;

public enum StatusPagamento {

	PAGO("Pago"),
	PROCESSANDO("Processando"),
	PENDENTE("Pendente");
	
	private String descricao;
	
	private StatusPagamento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
