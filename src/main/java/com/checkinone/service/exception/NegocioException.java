package com.checkinone.service.exception;

import java.util.List;

public class NegocioException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private List<String> mensagens;
	
	public NegocioException(String... mensagens) {
		this.mensagens = List.of(mensagens);
	}

	public List<String> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<String> mensagens) {
		this.mensagens = mensagens;
	}
}
