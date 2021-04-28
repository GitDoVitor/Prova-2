package com.uniamerica.prova2.service;

import com.uniamerica.prova2.model.Reserva;
import com.uniamerica.prova2.model.StatusReserva;
import com.uniamerica.prova2.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {

    final
    ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public Reserva insereReserva(Reserva reserva) throws Exception {
        reserva.setStatus(StatusReserva.reservado);
        return reservaRepository.save(reserva);
    }

    public Reserva iniciaReserva(Long id) throws Exception {
        Reserva reserva = reservaRepository.findReservaById(id);
        if (reserva.getStatus() != StatusReserva.reservado) {
            throw new Exception("Reserva não agendada");
        } else {
            reserva.setStatus(StatusReserva.em_andamento);
            return reservaRepository.save(reserva);
        }
    }

    public Reserva finalizaReserva(Long id) {
        Reserva reserva = reservaRepository.findReservaById(id);
        reserva.setStatus(StatusReserva.finalizado);
        return reservaRepository.save(reserva);
    }

    public List<Reserva> listaTodasReservas() {
        return reservaRepository.findAll();
    }


}
