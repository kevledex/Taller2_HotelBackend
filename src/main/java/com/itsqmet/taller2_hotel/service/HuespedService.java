package com.itsqmet.taller2_hotel.service;

import com.itsqmet.taller2_hotel.model.Huesped;
import com.itsqmet.taller2_hotel.repository.HuespedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HuespedService {

    @Autowired
    private HuespedRepository huespedRepository;

    public List<Huesped> obtenerTodo() {
        return huespedRepository.findAll();
    }

    public Optional<Huesped> buscarPorId(Long id) {
        return huespedRepository.findById(id);
    }

    public Optional<Huesped> buscarPorDocumento(String documento) {
        return huespedRepository.findByDocumentoIdentidad(documento);
    }

    public List<Huesped> buscarPorApellido(String apellido) {
        return huespedRepository.findByApellidoContainingIgnoreCase(apellido);
    }

    public Huesped crearHuesped(Huesped huesped) {
        return huespedRepository.save(huesped);
    }

    public Optional<Huesped> actualizar(Long id, Huesped huespedActualizado) {
        return huespedRepository.findById(id).map(huesped -> {
            huesped.setNombre(huespedActualizado.getNombre());
            huesped.setApellido(huespedActualizado.getApellido());
            huesped.setDocumentoIdentidad(huespedActualizado.getDocumentoIdentidad());
            huesped.setEmail(huespedActualizado.getEmail());
            huesped.setTelefono(huespedActualizado.getTelefono());
            return huespedRepository.save(huesped);
        });
    }

    public boolean eliminar(Long id) {
        if (huespedRepository.existsById(id)) {
            huespedRepository.deleteById(id);
            return true;
        }
        return false;
    }
}