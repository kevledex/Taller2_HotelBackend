package com.itsqmet.taller2_hotel.controller;

import com.itsqmet.taller2_hotel.model.Habitacion;
import com.itsqmet.taller2_hotel.service.HabitacionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/habitaciones")
@CrossOrigin("*")
public class HabitacionController {

    @Autowired
    private HabitacionService habitacionService;

    @GetMapping
    public ResponseEntity<List<Habitacion>> obtenerTodo() {
        List<Habitacion> habitaciones = habitacionService.obtenerTodo();
        return ResponseEntity.ok(habitaciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        return habitacionService.buscarPorId(id)
                .map(habitacion -> ResponseEntity.ok((Object) habitacion))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Habitación con id " + id + " no encontrada")));
    }

    @GetMapping("/piso/{piso}")
    public List<Habitacion> buscarPorPiso(@PathVariable Integer piso) {
        return habitacionService.buscarPorPiso(piso);
    }

    @GetMapping("/numero/{numero}")
    public Optional<Habitacion> buscarPorNumero(@PathVariable Integer numero) {
        return habitacionService.buscarPorNumero(numero);
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Habitacion habitacion, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(error -> errores.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        }

        Habitacion nueva = habitacionService.crearHabitacion(habitacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Habitacion habitacion,
                                        BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(error ->
                    errores.put(error.getField(), error.getDefaultMessage())
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        }

        return habitacionService.actualizar(id, habitacion)
                .map(actualizada -> ResponseEntity.ok((Object) actualizada))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Habitación con id " + id + " no encontrada")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (habitacionService.eliminar(id)) {
            return ResponseEntity.ok(Map.of("mensaje", "Habitación eliminada correctamente"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Habitación con id " + id + " no encontrada"));
    }
}