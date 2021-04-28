package com.uniamerica.prova2.service;

import com.uniamerica.prova2.model.Carro;
import com.uniamerica.prova2.model.Reserva;
import com.uniamerica.prova2.model.StatusReserva;
import com.uniamerica.prova2.repository.CarroRepository;
import com.uniamerica.prova2.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservaService {

    final ReservaRepository reservaRepository;
    final CarroRepository carroRepository;

    public ReservaService(ReservaRepository reservaRepository, CarroRepository carroRepository) {
        this.reservaRepository = reservaRepository;
        this.carroRepository = carroRepository;
    }

    public Reserva insereReserva(Reserva reserva) throws Exception {
        reserva.setStatus(StatusReserva.reservado);
        return reservaRepository.save(reserva);
    }

    public Reserva iniciaReserva(Long id) throws Exception {
        Reserva reserva = reservaRepository.getOne(id);
        if (reserva.getStatus() != StatusReserva.reservado) {
            throw new Exception("Reserva não agendada");
        } else {
            reserva.setStatus(StatusReserva.em_andamento);
            return reservaRepository.save(reserva);
        }
    }

    public Reserva finalizaReserva(Long id) {
        Reserva reserva = reservaRepository.getOne(id);
        reserva.setStatus(StatusReserva.finalizado);
        return reservaRepository.save(reserva);
    }

    public List<Reserva> listaTodasReservas() {
        return reservaRepository.findAll();
    }

    public List<Carro> listaCarrosReservados() {
        List<Reserva> listaDeReservas = reservaRepository.findAll();
        List<Carro> listaDeCarrosReservados = new ArrayList<>();

        for(Reserva temp : listaDeReservas) {
            listaDeCarrosReservados.add(temp.getCarroReservado());
        }
        return listaDeCarrosReservados;
    }
}
