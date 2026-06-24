package com.itsqmet.taller2_hotel.controller;

import com.itsqmet.taller2_hotel.model.Reserva;
import com.itsqmet.taller2_hotel.service.ReservaService;
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
@RequestMapping("/api/reservas")
@CrossOrigin("*")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public ResponseEntity<List<Reserva>> obtenerTodo() {
        List<Reserva> reservas = reservaService.obtenerTodo();
        return ResponseEntity.ok(reservas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        return reservaService.buscarPorId(id)
                .map(reserva -> ResponseEntity.ok((Object) reserva))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Reserva con id " + id + " no encontrada")));
    }

    @GetMapping("/estado/{estado}")
    public List<Reserva> buscarPorEstado(@PathVariable String estado) {
        return reservaService.buscarPorEstado(estado);
    }

    @GetMapping("/huesped/{huespedId}")
    public List<Reserva> buscarPorHuesped(@PathVariable Long huespedId) {
        return reservaService.buscarPorHuesped(huespedId);
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Reserva reserva, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(error ->
                    errores.put(error.getField(), error.getDefaultMessage())
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        }

        Reserva nueva = reservaService.crearReserva(reserva);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Reserva reserva,
                                        BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(error ->
                    errores.put(error.getField(), error.getDefaultMessage())
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        }

        return reservaService.actualizar(id, reserva)
                .map(actualizada -> ResponseEntity.ok((Object) actualizada))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Reserva con id " + id + " no encontrada")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (reservaService.eliminar(id)) {
            return ResponseEntity.ok(Map.of("mensaje", "Reserva eliminada correctamente"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "Reserva con id " + id + " no encontrada"));
    }
}