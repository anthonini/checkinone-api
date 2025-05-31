package com.checkinone.api.dto;

import java.time.LocalDateTime;

import com.checkinone.model.StatusReserva;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class ReservaDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull(message = "Data de entrada é campo obrigatório")
    private LocalDateTime dataEntrada;

    @NotNull(message = "Data de saída é campo obrigatório")
    private LocalDateTime dataSaida;

    @NotNull(message = "Status da reserva é campo obrigatório")
    private StatusReserva status;

    @NotNull(message = "Hóspede é campo obrigatório")
    private HospedeDTO hospede;

    @NotNull(message = "Quarto é campo obrigatório")
    private QuartoDTO quarto;
    
    @Valid
    private PagamentoDTO pagamento;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PagamentoDTO getPagamento() {
		return pagamento;
	}

	public void setPagamento(PagamentoDTO pagamento) {
		this.pagamento = pagamento;
	}

    public LocalDateTime getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDateTime dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public LocalDateTime getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(LocalDateTime dataSaida) {
		this.dataSaida = dataSaida;
	}

	public StatusReserva getStatus() {
        return status;
    }

    public void setStatus(StatusReserva status) {
        this.status = status;
    }

	public HospedeDTO getHospede() {
		return hospede;
	}

	public void setHospede(HospedeDTO hospede) {
		this.hospede = hospede;
	}

	public QuartoDTO getQuarto() {
		return quarto;
	}

	public void setQuarto(QuartoDTO quarto) {
		this.quarto = quarto;
	}
}
