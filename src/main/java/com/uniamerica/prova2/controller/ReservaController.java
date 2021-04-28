package com.uniamerica.prova2.controller;

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

    @Autowired
    ReservaService reservaService;

    @PostMapping
    ResponseEntity<?> insereReserva(@RequestBody Reserva reserva) throws Exception {
        Reserva add;
        try {
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

    @PostMapping("/inicia/{id}")
    public ResponseEntity<?> iniciaReserva(@PathVariable Long id) throws Exception {
        Reserva add;
        try {
            add = reservaService.iniciaReserva(id);
        } catch (Exception e) {
            throw new Exception(e);
        }
        return new ResponseEntity<>(add, null, HttpStatus.OK);
    }

    @PostMapping("/finaliza/{id}")
    public ResponseEntity<?> finalizaReserva(@PathVariable Long id) throws Exception {
        Reserva add;
        try {
            add = reservaService.finalizaReserva(id);
        } catch (Exception e) {
            throw new Exception(e);
        }
        return new ResponseEntity<>(add, null, HttpStatus.OK);
    }
}
