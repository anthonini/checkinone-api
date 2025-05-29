package com.checkinone.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.checkinone.model.FormaPagamento;
import com.checkinone.model.StatusPagamento;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class PagamentoDTO {

	private Long id;
	
	@NotNull(message = "Valor é obrigatório")
	@DecimalMin(value = "0.00", message = "O valor deve ser maior que R$0,00")
	@DecimalMax(value = "9999999.99", message = "Valor deve ser menor ou igual a R$9.999.999,99")
	private BigDecimal valor;

	@NotNull(message = "Forma de pagamento é obrigatório")
	private FormaPagamento formaPagamento;
	
	private LocalDateTime data;

	private StatusPagamento status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public StatusPagamento getStatus() {
		return status;
	}

	public void setStatus(StatusPagamento status) {
		this.status = status;
	}
}
