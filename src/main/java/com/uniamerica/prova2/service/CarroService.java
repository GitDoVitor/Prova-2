package com.uniamerica.prova2.service;

import com.uniamerica.prova2.model.Carro;
import com.uniamerica.prova2.model.Marca;
import com.uniamerica.prova2.model.Reserva;
import com.uniamerica.prova2.repository.CarroRepository;
import com.uniamerica.prova2.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarroService {

    private final CarroRepository carroRepository;
    private final ReservaRepository reservaRepository;

    @Autowired
    public CarroService(CarroRepository carroRepository, ReservaRepository reservaRepository) {
        this.carroRepository = carroRepository;
        this.reservaRepository = reservaRepository;
    }

    public Carro insereCarro(Carro carro){
        return carroRepository.save(carro);
    }

    public List<Carro> getCarrosByMarca(Marca marca) {

        List<Carro> carros = carroRepository.findAll();

        return carros.stream()
                .filter(carro -> carro.getModelo().getMarca().equals(marca))
                .collect(Collectors.toList());
    }

    public List<Carro> listaCarrosPorData(LocalDate dataRetirada, LocalDate dataEntrega) {
        List<Carro> listaNaoFiltrada = carroRepository.findAll();
        List<Reserva> listaDeReservas = reservaRepository.findAll();
        List<Reserva> listaDeConflitos = new ArrayList<>();
        List<Carro> listaDeCarrosComConflito = new ArrayList<>();

        for(Reserva temp : listaDeReservas){
            if(temp.getDataRetirada().compareTo(dataRetirada) <= 0 && temp.getDataEntrega().compareTo(dataEntrega) >= 0) {
                listaDeConflitos.add(temp);
            } else if(temp.getDataEntrega().compareTo(dataRetirada) >= 0 && temp.getDataEntrega().compareTo(dataEntrega) <= 0) {
                listaDeConflitos.add(temp);
            } else if(temp.getDataRetirada().compareTo(dataRetirada) >= 0 && temp.getDataRetirada().compareTo(dataEntrega) <= 0) {
                listaDeConflitos.add(temp);
            }
        }

        for(Reserva temp2 : listaDeConflitos) {
            listaDeCarrosComConflito.add(temp2.getCarroReservado());
        }

        return listaNaoFiltrada.stream()
                .filter(e -> !listaDeCarrosComConflito.contains(e))
                .collect (Collectors.toList());
    }


}
