package com.itsqmet.taller2_hotel.service;

import com.itsqmet.taller2_hotel.model.Habitacion;
import com.itsqmet.taller2_hotel.repository.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HabitacionService {

    @Autowired
    private HabitacionRepository habitacionRepository;

    public List<Habitacion> obtenerTodo() {
        return habitacionRepository.findAll();
    }

    public Optional<Habitacion> buscarPorId(Long id) {
        return habitacionRepository.findById(id);
    }

    public List<Habitacion> buscarPorPiso(Integer piso) {
        return habitacionRepository.findByPiso(piso);
    }

    public Optional<Habitacion> buscarPorNumero(Integer numero) {
        return habitacionRepository.findByNumero(numero);
    }

    public Habitacion crearHabitacion(Habitacion habitacion) {
        return habitacionRepository.save(habitacion);
    }

    public Optional<Habitacion> actualizar(Long id, Habitacion habitacionActualizada) {
        return habitacionRepository.findById(id).map(habitacion -> {
            habitacion.setNumero(habitacionActualizada.getNumero());
            habitacion.setPiso(habitacionActualizada.getPiso());
            habitacion.setCapacidad(habitacionActualizada.getCapacidad());
            return habitacionRepository.save(habitacion);
        });
    }

    public boolean eliminar(Long id) {
        if (habitacionRepository.existsById(id)) {
            habitacionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}