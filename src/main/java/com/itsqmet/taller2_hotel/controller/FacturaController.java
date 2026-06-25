package com.itsqmet.taller2_hotel.controller;

import com.itsqmet.taller2_hotel.model.Factura;
import com.itsqmet.taller2_hotel.service.FacturaService;
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
@RequestMapping("/api/facturas")
@CrossOrigin("*")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @GetMapping
    public ResponseEntity<List<Factura>> obtenerTodo() {
        List<Factura> facturas = facturaService.obtenerTodo();
        return ResponseEntity.ok(facturas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        return facturaService.buscarPorId(id)
                .map(factura -> ResponseEntity.ok((Object) factura))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Factura con id " + id + " no encontrada")));
    }

    @GetMapping("/numero/{numeroFactura}")
    public ResponseEntity<?> buscarPorNumeroFactura(@PathVariable String numeroFactura) {
        return facturaService.buscarPorNumeroFactura(numeroFactura)
                .map(factura -> ResponseEntity.ok((Object) factura))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Factura con número " + numeroFactura + " no encontrada")));
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Factura factura, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(error -> errores.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        }

        Factura nueva = facturaService.crearFactura(factura);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Factura factura,
                                        BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(error -> errores.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        }

        return facturaService.actualizar(id, factura)
                .map(actualizada -> ResponseEntity.ok((Object) actualizada))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Factura con id " + id + " no encontrada")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (facturaService.eliminar(id)) {
            return ResponseEntity.ok(Map.of("mensaje", "Factura eliminada correctamente"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "Factura con id " + id + " no encontrada"));
    }
}