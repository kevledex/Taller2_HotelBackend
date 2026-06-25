package com.itsqmet.taller2_hotel.service;

import com.itsqmet.taller2_hotel.model.Empleado;
import com.itsqmet.taller2_hotel.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public List<Empleado> obtenerTodo() {
        return empleadoRepository.findAll();
    }

    public Optional<Empleado> buscarPorId(Long id) {
        return empleadoRepository.findById(id);
    }

    public Optional<Empleado> buscarPorEmail(String email) {
        return empleadoRepository.findByEmail(email);
    }

    public List<Empleado> buscarPorApellido(String apellido) {
        return empleadoRepository.findByApellidoContainingIgnoreCase(apellido);
    }

    public List<Empleado> buscarPorCargo(String cargo) {
        return empleadoRepository.findByCargoContainingIgnoreCase(cargo);
    }

    public Empleado crearEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    public Optional<Empleado> actualizar(Long id, Empleado empleadoActualizado) {
        return empleadoRepository.findById(id).map(empleado -> {
            empleado.setNombre(empleadoActualizado.getNombre());
            empleado.setApellido(empleadoActualizado.getApellido());
            empleado.setCargo(empleadoActualizado.getCargo());
            empleado.setEmail(empleadoActualizado.getEmail());
            empleado.setTelefono(empleadoActualizado.getTelefono());
            return empleadoRepository.save(empleado);
        });
    }

    public boolean eliminar(Long id) {
        if (empleadoRepository.existsById(id)) {
            empleadoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}