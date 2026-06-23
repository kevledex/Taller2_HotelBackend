package com.itsqmet.taller2_hotel.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import jakarta.validation.constraints.*;

@Entity
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "El campo cantidad de personas no puede estar vacio")
    @Min(value = 1, message = "La cantidad de personas debe ser al menos 1")
    @Column(nullable = false)
    private Integer cantidadPersonas;

    @NotNull(message = "El campo precio total no puede estar vacio")
    @Min(value = 0, message = "El precio total no puede ser negativo")
    @Column(nullable = false)
    private double precioTotal;

    @NotBlank(message = "El campo estado de la reserva no puede estar vacio")
    @Size(min = 3, max = 20, message = "El estado debe tener entre 3 y 20 caracteres")
    @Column(nullable = false, length = 20)
    private String estadoReserva;

    @Size(max = 500, message = "Las observaciones no pueden superar los 500 caracteres")
    @Column(length = 500)
    private String observaciones;

    @NotNull(message = "La reserva debe estar asignada a un huesped")
    @ManyToOne
    @JoinColumn(name = "huesped_id", referencedColumnName = "idHuesped", nullable = false)
    @JsonManagedReference("huesped-reserva")
    private Huesped huesped;

    @OneToOne
    @JoinColumn(name = "factura_id")
    @JsonBackReference("reserva-factura")
    private Factura factura;

    public Reserva() {

    }

    public Reserva(Long id, Integer cantidadPersonas, double precioTotal, String estadoReserva, String observaciones, Huesped huesped, Factura factura) {
        this.id = id;
        this.cantidadPersonas = cantidadPersonas;
        this.precioTotal = precioTotal;
        this.estadoReserva = estadoReserva;
        this.observaciones = observaciones;
        this.huesped = huesped;
        this.factura = factura;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(Integer cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String getEstadoReserva() {
        return estadoReserva;
    }

    public void setEstadoReserva(String estadoReserva) {
        this.estadoReserva = estadoReserva;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Huesped getHuesped() {
        return huesped;
    }

    public void setHuesped(Huesped huesped) {
        this.huesped = huesped;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }
}
