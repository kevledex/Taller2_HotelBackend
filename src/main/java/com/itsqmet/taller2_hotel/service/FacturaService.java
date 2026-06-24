package com.itsqmet.taller2_hotel.service;

import com.itsqmet.taller2_hotel.model.Factura;
import com.itsqmet.taller2_hotel.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    public List<Factura> obtenerTodo() {
        return facturaRepository.findAll();
    }

    public Optional<Factura> buscarPorId(Long id) {
        return facturaRepository.findById(id);
    }

    public Optional<Factura> buscarPorNumeroFactura(String numeroFactura) {
        return facturaRepository.findByNumeroFactura(numeroFactura);
    }

    public List<Factura> buscarPorHuesped(Long huespedId) {
        return facturaRepository.findByHuespedId(huespedId);
    }

    public Factura crearFactura(Factura factura) {
        return facturaRepository.save(factura);
    }

    public Optional<Factura> actualizar(Long id, Factura facturaActualizada) {
        return facturaRepository.findById(id).map(factura -> {
            factura.setNumeroFactura(facturaActualizada.getNumeroFactura());
            factura.setFechaEmision(facturaActualizada.getFechaEmision());
            factura.setTotal(facturaActualizada.getTotal());
            factura.setEstado(facturaActualizada.getEstado());
            // Si necesitas actualizar la relación al cambiar de huésped:
            factura.setHuesped(facturaActualizada.getHuesped());
            return facturaRepository.save(factura);
        });
    }

    public boolean eliminar(Long id) {
        if (facturaRepository.existsById(id)) {
            facturaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}