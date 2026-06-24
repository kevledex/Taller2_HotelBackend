package com.itsqmet.taller2_hotel.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;

@Entity
@Table(name = "huespedes")
public class Huesped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idHuesped")
    private Long idHuesped;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    @Column(nullable = false, length = 50)
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(min = 3, max = 50, message = "El apellido debe tener entre 3 y 50 caracteres")
    @Column(nullable = false, length = 50)
    private String apellido;

    @NotBlank(message = "La cédula no puede estar vacía")
    @Size(min = 10, max = 10, message = "La cédula debe tener 10 dígitos")
    @Column(nullable = false, unique = true, length = 10)
    private String cedula;

    @Email(message = "Ingrese un correo válido")
    @NotBlank(message = "El correo no puede estar vacío")
    @Column(nullable = false, unique = true)
    private String correo;

    @NotBlank(message = "El teléfono no puede estar vacío")
    @Column(nullable = false, length = 15)
    private String telefono;

    // RELACIÓN 1:N INVERSA
    @OneToMany(mappedBy = "huesped")
    @JsonBackReference("huesped-reserva")
    private List<Reserva> reservas;

    public Huesped() {

    }

    public Huesped(Long idHuesped, String nombre, String apellido, String cedula, String correo, String telefono, List<Reserva> reservas) {
        this.idHuesped = idHuesped;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.correo = correo;
        this.telefono = telefono;
        this.reservas = reservas;
    }

    public Long getIdHuesped() {
        return idHuesped;
    }

    public void setIdHuesped(Long idHuesped) {
        this.idHuesped = idHuesped;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
}
