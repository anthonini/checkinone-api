package com.checkinone.api.dto;

import java.util.Date;

import com.checkinone.model.StatusReserva;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class ReservaDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull(message = "Data de entrada é campo obrigatório")
    private Date dataEntrada;

    @NotNull(message = "Data de saída é campo obrigatório")
    private Date dataSaida;

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

	public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
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
