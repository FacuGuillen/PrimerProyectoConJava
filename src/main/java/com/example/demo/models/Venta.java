package com.example.demo.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Venta {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Schema(description = "Nombre del vendedor", requiredMode = Schema.RequiredMode.REQUIRED, example = "Federico")
    private String nombreVendedor;

    @Column
    @Schema(description = "Cuit o Cuil del comprador sin guiones", requiredMode = Schema.RequiredMode.REQUIRED, example = "20385554840")
    private int cuitComprador;

    @Column
    @Schema(description = "Cantidad compra del mismo producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "3")
    private int cantidadCompraProducto;

    @Column
    @Schema (description = "Precio por cantidad de productos")
    private double precioPorProductos;

    @ManyToOne
    @JoinColumn(name = "factura_id")
    private Factura factura;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreVendedor() {
        return nombreVendedor;
    }

    public void setNombreVendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
    }

    public int getCantidadCompraProducto() {
        return cantidadCompraProducto;
    }

    public void setCantidadCompraProducto(int cantidadCompraProducto) {
        this.cantidadCompraProducto = cantidadCompraProducto;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCuitComprador() {
        return cuitComprador;
    }

    public void setCuitComprador(int cuitComprador) {
        this.cuitComprador = cuitComprador;
    }

    public double getPrecioPorProductos() {
        return precioPorProductos;
    }

    public void setPrecioPorProductos(double precioPorProductos) {
        this.precioPorProductos = precioPorProductos;
    }
}
