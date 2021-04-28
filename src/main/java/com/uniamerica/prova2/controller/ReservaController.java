package com.uniamerica.prova2.controller;

import com.uniamerica.prova2.model.Carro;
import com.uniamerica.prova2.model.Reserva;
import com.uniamerica.prova2.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reserva")
public class ReservaController {

    final
    ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping
    ResponseEntity<?> insereReserva(@RequestBody Reserva reserva) throws Exception {
        List<Carro> listaDeCarrosReservados = reservaService.listaCarrosReservados();
        Reserva add;
        try {
            for(Carro temp : listaDeCarrosReservados){
                if(temp.getId().equals(reserva.getCarroReservado().getId())){
                    throw new Exception("Carro reservado!");
                }
            }
            add = reservaService.insereReserva(reserva);
        } catch (Exception e) {
            throw new Exception(e);
        }
        return new ResponseEntity<>(add, null, HttpStatus.CREATED);
    }

    @GetMapping
    public List<?> listaTodasReservas() {
        return reservaService.listaTodasReservas();
    }

    @PutMapping("/inicia/{id}")
    public ResponseEntity<?> iniciaReserva(@PathVariable(value = "id") Long id) throws Exception {
        Reserva add;
        try {
            add = reservaService.iniciaReserva(id);
        } catch (Exception e) {
            throw new Exception(e);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/finaliza/{id}")
    public ResponseEntity<?> finalizaReserva(@PathVariable(value = "id") Long id) throws Exception {
        Reserva add;
        try {
            add = reservaService.finalizaReserva(id);
        } catch (Exception e) {
            throw new Exception(e);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
