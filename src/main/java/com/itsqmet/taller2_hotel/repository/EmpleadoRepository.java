package com.itsqmet.taller2_hotel.repository;

import com.itsqmet.taller2_hotel.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    Optional<Empleado> findByEmail(String email);

    List<Empleado> findByApellidoContainingIgnoreCase(String apellido);

    List<Empleado> findByCargoContainingIgnoreCase(String cargo);
}