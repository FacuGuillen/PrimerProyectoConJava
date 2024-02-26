package com.example.demo.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.graphql.ConditionalOnGraphQlSchema;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity // Representa una entidad, crea una tabla en la base de datos
@Getter
@Setter
public class Cliente {

    // Atributos
    @Id // Representa que sera el ID de mi tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremento de la PK
    @Schema(description = "Usuario autogestionado por DB", requiredMode = Schema.RequiredMode.AUTO, example = "1")
    private Long id;

    @Column // Hace referencia a una columna de la tabla
    @Schema(description = "Nombre del cliente", requiredMode = Schema.RequiredMode.REQUIRED, example = "Facundo")
    private String nombre;

    @Column(name = "correo") // Me renombra la tabla
    @Schema(description = "Email del cliente", requiredMode = Schema.RequiredMode.REQUIRED, example = "facu@hotmail.com")
    private String email;

    @Column
    @Schema(description = "Fecha de nacimiento", requiredMode = Schema.RequiredMode.REQUIRED, example = "aaaa,mm,dd")
    private LocalDate fechaNacimiento;

    @Column
    @Schema(description = "Edad calcualda por al fecha de nacimiento")
    private int edad;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Factura> factura = new ArrayList<>();


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
