package com.itsqmet.taller2_hotel.repository;

import com.itsqmet.taller2_hotel.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    // Buscar un empleado por su correo electrónico
    Optional<Empleado> findByEmail(String email);

    // Buscar empleados por apellido
    List<Empleado> findByApellidoContainingIgnoreCase(String apellido);

    // Buscar empleados por cargo
    List<Empleado> findByCargoContainingIgnoreCase(String cargo);
}