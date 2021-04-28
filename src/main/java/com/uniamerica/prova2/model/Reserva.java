package com.uniamerica.prova2.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Carro carroReservado;

    private LocalDate dataRetirada;

    private LocalDate dataEntrega;

    private StatusReserva status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(LocalDate dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public StatusReserva getStatus() {
        return status;
    }

    public void setStatus(StatusReserva status) {
        this.status = status;
    }

    public Carro getCarroReservado() {
        return carroReservado;
    }

    public void setCarroReservado(Carro carroReservado) {
        this.carroReservado = carroReservado;
    }
}
