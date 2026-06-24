package com.itsqmet.taller2_hotel.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "facturas")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "El número de factura no puede estar vacío")
    @Pattern(regexp = "^\\d{3}-\\d{3}-\\d{9}$", message = "El formato de la factura debe ser 001-001-000000001")
    @Column(nullable = false, unique = true, length = 17)
    private String numeroFactura;

    @NotNull(message = "La fecha de emisión no puede ser nula")
    @Column(nullable = false)
    private LocalDateTime fechaEmision;

    @NotNull(message = "El subtotal no puede estar vacío")
    @DecimalMin(value = "0.0", inclusive = false, message = "El subtotal debe ser mayor a 0")
    @Column(nullable = false)
    private Double subtotal;

    @NotNull(message = "El IVA no puede estar vacío")
    @DecimalMin(value = "0.0", message = "El IVA no puede ser negativo")
    @Column(nullable = false)
    private Double iva;

    @NotNull(message = "El total no puede estar vacío")
    @DecimalMin(value = "0.0", inclusive = false, message = "El total debe ser mayor a 0")
    @Column(nullable = false)
    private Double total;

    @OneToOne
    @JoinColumn(name = "reserva_id", nullable = false)
    @JsonBackReference("reserva-factura")
    private Reserva reserva;

    public Factura() {

    }

    public Factura(Long id, String numeroFactura, LocalDateTime fechaEmision, Double subtotal, Double iva, Double total, Reserva reserva) {
        this.id = id;
        this.numeroFactura = numeroFactura;
        this.fechaEmision = fechaEmision;
        this.subtotal = subtotal;
        this.iva = iva;
        this.total = total;
        this.reserva = reserva;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public LocalDateTime getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDateTime fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
        this.iva = iva;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
}
