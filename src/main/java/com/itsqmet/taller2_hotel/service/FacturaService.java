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

    public List<Factura> buscarPorHuespedId(Long huespedId) {
        return facturaRepository.findByReserva_Huesped_Id(huespedId);
    }

    public Factura crearFactura(Factura factura) {
        return facturaRepository.save(factura);
    }

    public Optional<Factura> actualizar(Long id, Factura facturaActualizada) {
        return facturaRepository.findById(id).map(factura -> {
            factura.setNumeroFactura(facturaActualizada.getNumeroFactura());
            factura.setFechaEmision(facturaActualizada.getFechaEmision());
            factura.setSubtotal(facturaActualizada.getSubtotal());
            factura.setIva(facturaActualizada.getIva());
            factura.setTotal(facturaActualizada.getTotal());
            factura.setReserva(facturaActualizada.getReserva()); // Actualiza la relación OneToOne con Reserva
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