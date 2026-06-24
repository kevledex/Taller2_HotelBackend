package com.itsqmet.taller2_hotel.repository;

import com.itsqmet.taller2_hotel.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    List<Reserva> findByEstadoReservaIgnoreCase(String estadoReserva);

    List<Reserva> findByHuespedId(Long huespedId);
}