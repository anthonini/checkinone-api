package com.checkinone.api.dto;

import com.checkinone.model.Hotel;
import com.checkinone.model.StatusOcupacao;
import com.checkinone.model.TipoQuarto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class QuartoDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "Número é campo obrigatório")
    private String numero;

    @NotBlank(message = "Tipo de Quarto é campo obrigatório")
    private TipoQuarto tipo;

    @NotNull(message = "Capacidade é campo obrigatório")
    @Min(value = 1, message = "Capacidade deve ser maior que zero")
    private Integer capacidade;

    @NotNull(message = "Valor da diária é obrigatório")
    @DecimalMin(value = "0.00", message = "O valor deve ser maior que R$0,00")
    @DecimalMax(value = "9999999.99", message = "Valor deve ser menor ou igual a R$9.999.999,99")
    private BigDecimal valorDiaria;

    @NotBlank(message = "Status de ocupação do quarto é campo obrigatório")
    private StatusOcupacao status;

    @NotBlank(message = "Hotel é campo obrigatório")
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

    public void setCapacidade() {
        this.capacidade = capacidade;
    }
}
