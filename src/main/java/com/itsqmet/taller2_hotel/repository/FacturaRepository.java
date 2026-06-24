package com.itsqmet.taller2_hotel.repository;

import com.itsqmet.taller2_hotel.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {

    // Buscar una factura específica por su número correlativo
    Optional<Factura> findByNumeroFactura(String numeroFactura);

    // Buscar todas las facturas asociadas a un huésped específico por su ID
    List<Factura> findByHuespedId(Long huespedId);
}