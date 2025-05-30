package com.checkinone.api.dto;

import com.checkinone.model.Hospede;
import com.checkinone.model.Pagamento;
import com.checkinone.model.Quarto;
import com.checkinone.model.StatusReserva;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;

public class ReservaDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private Pagamento pagamento;

    @NotNull(message = "Data de entrada é campo obrigatório")
    private Date dataEntrada;

    @NotNull(message = "Data de saída é campo obrigatório")
    private Date dataSaida;

    @NotBlank(message = "Status da reserva é campo obrigatório")
    private StatusReserva status;

    @NotNull(message = "Valor da reserva é obrigatório")
    @DecimalMin(value = "0.00", message = "O valor deve ser maior que R$0,00")
    @DecimalMax(value = "9999999.99", message = "Valor deve ser menor ou igual a R$9.999.999,99")
    private BigDecimal valor;

    @NotNull(message = "Hóspede responsável é campo obrigatório")
    private Hospede hospedeResponsavel;

    @NotNull(message = "Quarto é campo obrigatório")
    private Quarto quarto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
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

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Hospede getHospedeResponsavel() {
        return hospedeResponsavel;
    }

    public void setHospedeResponsavel(Hospede hospedeResponsavel) {
        this.hospedeResponsavel = hospedeResponsavel;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }
}
