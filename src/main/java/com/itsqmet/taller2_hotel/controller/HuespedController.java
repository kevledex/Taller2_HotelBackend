package com.itsqmet.taller2_hotel.controller;

import com.itsqmet.taller2_hotel.model.Huesped;
import com.itsqmet.taller2_hotel.service.HuespedService;
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
@RequestMapping("/api/huespedes")
@CrossOrigin("*")
public class HuespedController {

    @Autowired
    private HuespedService huespedService;

    @GetMapping
    public ResponseEntity<List<Huesped>> obtenerTodo() {
        List<Huesped> huespedes = huespedService.obtenerTodo();
        return ResponseEntity.ok(huespedes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        return huespedService.buscarPorId(id)
                .map(huesped -> ResponseEntity.ok((Object) huesped))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Huésped con id " + id + " no encontrado")));
    }

    @GetMapping("/documento/{documento}")
    public ResponseEntity<?> buscarPorDocumento(@PathVariable String documento) {
        return huespedService.buscarPorDocumento(documento)
                .map(huesped -> ResponseEntity.ok((Object) huesped))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Huésped con documento " + documento + " no encontrado")));
    }

    @GetMapping("/apellido/{apellido}")
    public List<Huesped> buscarPorApellido(@PathVariable String apellido) {
        return huespedService.buscarPorApellido(apellido);
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Huesped huesped, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(error -> errores.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        }

        Huesped nuevo = huespedService.crearHuesped(huesped);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Huesped huesped,
                                        BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(error -> errores.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        }

        return huespedService.actualizar(id, huesped)
                .map(actualizado -> ResponseEntity.ok((Object) actualizado))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Huésped con id " + id + " no encontrado")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (huespedService.eliminar(id)) {
            return ResponseEntity.ok(Map.of("mensaje", "Huésped eliminado correctamente"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "Huésped con id " + id + " no encontrado"));
    }
}