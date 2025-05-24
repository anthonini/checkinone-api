package com.checkinone.model;

public enum FormaPagamento {
	
	CARTAO("Cartão"),
	PIX("Pix"),
	BOLETO("Boleto");
	
	private String descricao;
	
	private FormaPagamento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
