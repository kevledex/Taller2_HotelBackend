package com.itsqmet.taller2_hotel.controller;

import com.itsqmet.taller2_hotel.model.Empleado;
import com.itsqmet.taller2_hotel.service.EmpleadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/empleados")
@CrossOrigin("*")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public ResponseEntity<List<Empleado>> obtenerTodo() {
        List<Empleado> empleados = empleadoService.obtenerTodo();
        return ResponseEntity.ok(empleados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        return empleadoService.buscarPorId(id)
                .map(empleado -> ResponseEntity.ok((Object) empleado))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Empleado con id " + id + " no encontrado")));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> buscarPorEmail(@PathVariable String email) {
        return empleadoService.buscarPorEmail(email)
                .map(empleado -> ResponseEntity.ok((Object) empleado))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Empleado con email " + email + " no encontrado")));
    }

    @GetMapping("/apellido/{apellido}")
    public List<Empleado> buscarPorApellido(@PathVariable String apellido) {
        return empleadoService.buscarPorApellido(apellido);
    }

    @GetMapping("/cargo/{cargo}")
    public List<Empleado> buscarPorCargo(@PathVariable String cargo) {
        return empleadoService.buscarPorCargo(cargo);
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Empleado empleado, BindingResult result) {

        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(error ->
                    errores.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        }

        Empleado nuevo = empleadoService.crearEmpleado(empleado);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @Valid @RequestBody Empleado empleado,
                                        BindingResult result) {

        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(error ->
                    errores.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        }

        return empleadoService.actualizar(id, empleado)
                .map(actualizado -> ResponseEntity.ok((Object) actualizado))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Empleado con id " + id + " no encontrado")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {

        if (empleadoService.eliminar(id)) {
            return ResponseEntity.ok(
                    Map.of("mensaje", "Empleado eliminado correctamente"));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "Empleado con id " + id + " no encontrado"));
    }
}