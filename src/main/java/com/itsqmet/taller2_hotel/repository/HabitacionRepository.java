package com.itsqmet.taller2_hotel.repository;

import com.itsqmet.taller2_hotel.model.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {

    List<Habitacion> findByPiso(Integer piso);

    Optional<Habitacion> findByNumero(Integer numero);
}