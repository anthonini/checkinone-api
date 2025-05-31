package com.checkinone.api.dto;

import java.math.BigDecimal;

import com.checkinone.model.Hotel;
import com.checkinone.model.StatusOcupacao;
import com.checkinone.model.TipoQuarto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class QuartoDTO {

    private Long id;

    @NotNull(message = "Número é campo obrigatório")
    private String numero;

    @NotNull(message = "Tipo de Quarto é campo obrigatório")
    private TipoQuarto tipo;

    @NotNull(message = "Capacidade é campo obrigatório")
    @Min(value = 1, message = "Capacidade deve ser maior que zero")
    private Integer capacidade;

    @NotNull(message = "Valor da diária é obrigatório")
    @DecimalMin(value = "0.00", message = "O valor deve ser maior que R$0,00")
    @DecimalMax(value = "9999999.99", message = "Valor deve ser menor ou igual a R$9.999.999,99")
    private BigDecimal valorDiaria;

    @NotNull(message = "Status de ocupação do quarto é campo obrigatório")
    private StatusOcupacao status;

    @NotNull(message = "Hotel é campo obrigatório")
    private Hotel hotel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public TipoQuarto getTipo() {
        return tipo;
    }

    public void setTipo(TipoQuarto tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(BigDecimal valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public StatusOcupacao getStatus() {
        return status;
    }

    public void setStatus(StatusOcupacao status) {
        this.status = status;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }
}
