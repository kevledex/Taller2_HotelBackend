package com.itsqmet.taller2_hotel.service;

import com.itsqmet.taller2_hotel.model.Habitacion;
import com.itsqmet.taller2_hotel.model.Reserva;
import com.itsqmet.taller2_hotel.repository.HabitacionRepository;
import com.itsqmet.taller2_hotel.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private HabitacionRepository habitacionRepository;

    public List<Reserva> obtenerTodo() {
        return reservaRepository.findAll();
    }

    public Optional<Reserva> buscarPorId(Long id) {
        return reservaRepository.findById(id);
    }

    public List<Reserva> buscarPorEstado(String estado) {
        return reservaRepository.findByEstadoReservaIgnoreCase(estado);
    }

    public List<Reserva> buscarPorHuesped(Long huespedId) {
        return reservaRepository.findByHuespedId(huespedId);
    }

    public Reserva crearReserva(Reserva reserva) {
        List<Habitacion> habitacionesCompletas = reserva.getHabitaciones().stream()
                .map(h -> habitacionRepository.findById(h.getId()).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        reserva.setHabitaciones(habitacionesCompletas);
        return reservaRepository.save(reserva);
    }

    public Optional<Reserva> actualizar(Long id, Reserva reservaActualizada) {
        return reservaRepository.findById(id).map(reserva -> {
            reserva.setCantidadPersonas(reservaActualizada.getCantidadPersonas());
            reserva.setPrecioTotal(reservaActualizada.getPrecioTotal());
            reserva.setEstadoReserva(reservaActualizada.getEstadoReserva());
            reserva.setObservaciones(reservaActualizada.getObservaciones());
            reserva.setHuesped(reservaActualizada.getHuesped());
            reserva.setFactura(reservaActualizada.getFactura());
            return reservaRepository.save(reserva);
        });
    }

    public boolean eliminar(Long id) {
        if (reservaRepository.existsById(id)) {
            reservaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}