package com.itsqmet.taller2_hotel.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import jakarta.validation.constraints.*;

@Entity
@Table(name = "habitaciones")
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "El campo numero de habitacion no puede estar vacia")
    @Min(value = 1, message = "El numero de piso debe ser al menos 1")
    @Column(nullable = false)
    private Integer numero;

    @NotNull(message = "El campo piso de habitacion no puede estar vacio")
    @Min(value = 1, message = "El piso de la habitacion debe ser al menos 1")
    @Column(nullable = false)
    private Integer piso;

    @Min(value = 1, message = "La capacidad debe ser al menos 1 persona")
    @Max(value = 10, message = "La capacidad máxima es de 10 personas")
    private Integer capacidad;

    public Habitacion() {

    }

    public Habitacion(Long id, Integer numero, Integer piso, Integer capacidad) {
        this.id = id;
        this.numero = numero;
        this.piso = piso;
        this.capacidad = capacidad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getPiso() {
        return piso;
    }

    public void setPiso(Integer piso) {
        this.piso = piso;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }
}

