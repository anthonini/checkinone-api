package com.checkinone.model;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name= "quarto")
@DynamicUpdate
public class Quarto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_quarto")
    private Long id;

    @Column(name = "numero")
    private String numero;

    @Enumerated(EnumType.STRING)
    @Column(name = "id_tipo_quarto")
    private TipoQuarto tipo;

    @Column(name = "capacidade")
    private Integer capacidade;

    @Column(name = "valor_diaria")
    private BigDecimal valorDiaria;

    @Enumerated(EnumType.STRING)
    @Column(name = "id_status_ocupacao")
    private StatusOcupacao status;

    @ManyToOne
    @JoinColumn(name = "id_hotel")
    private Hotel hotel;

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(BigDecimal valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public TipoQuarto getTipo() {
        return tipo;
    }

    public void setTipo(TipoQuarto tipo) {
        this.tipo = tipo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public StatusOcupacao getStatus() {
        return status;
    }

    public void setStatus(StatusOcupacao status) {
        this.status = status;
    }
}
