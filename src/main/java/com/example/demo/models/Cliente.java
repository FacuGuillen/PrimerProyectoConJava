package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Entity // Representa una entidad, crea una tabla en la base de datos
@Getter
@Setter
public class Cliente {

    // Atributos
    @Id // Representa que sera el ID de mi tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremento de la PK
    private Long id;

    @Column // Hace referencia a una columna de la tabla
    private String nombre;

    @Column(name = "correo") // Me renombra la tabla
    private String email;

    @Column
    private LocalDate fechaNacimiento;

    @Column
    private int edad;



// Getters and Setters

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

}
