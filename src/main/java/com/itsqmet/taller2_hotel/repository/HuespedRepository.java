package com.itsqmet.taller2_hotel.repository;

import com.itsqmet.taller2_hotel.model.Huesped;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HuespedRepository extends JpaRepository<Huesped, Long> {

    // Buscar un huésped por su documento de identidad (cédula o pasaporte)
    Optional<Huesped> findByDocumento(String documento);

    // Buscar huéspedes por apellido (por si quieres listar coincidencias)
    List<Huesped> findByApellidoContainingIgnoreCase(String apellido);
}