package com.example.demo.models;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter

public class Producto {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Schema(description = "Nombre del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "Smart tv LG")
    private String descripcion;

    @Column
    @Schema(description = "Precio del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "100000.00")
    private Double precio;

    @Column
    @Schema(description = "Stock del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "3")
    private int stock;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List <Venta> ventas = new ArrayList<>();


    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}