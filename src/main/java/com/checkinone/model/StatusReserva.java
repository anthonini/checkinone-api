package com.checkinone.model;

public enum StatusReserva {
	ATIVA("Ativa"), 
	CANCELADA("Cancelada"),
	CONCLUIDA("Concluída");
	
	private String descricao;
	
	private StatusReserva(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
