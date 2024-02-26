package com.example.demo.services;

import com.example.demo.models.Cliente;
import com.example.demo.models.Factura;
import com.example.demo.models.Producto;
import com.example.demo.models.Venta;
import com.example.demo.repository.RepositoryCliente;
import com.example.demo.repository.RepositoryFactura;
import com.example.demo.repository.RepositoryProducto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Service
public class ServiceFactura {

    @Autowired
    private RepositoryFactura repoFactura;

    @Autowired
    private RepositoryCliente repoCliente;

    @Autowired
    private RepositoryProducto repoProducto;

    public void FechaFactura(Factura factura) {
        RestTemplate restTemplate = new RestTemplate();
        String api = "http://worldclockapi.com/api/json/utc/now";
        ResponseEntity<String> response = restTemplate.getForEntity(api, String.class);

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode jsonNode = objectMapper.readTree(response.getBody());

            String currentDateTime = jsonNode.get("currentDateTime").asText();

            // Definir el formato de la fecha esperado
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mmX");

            // Parsear la fecha a LocalDateTime
            LocalDateTime fecha = LocalDateTime.parse(currentDateTime, formatter);

            factura.setFechaFactura(fecha);

        } catch (JsonProcessingException e) {
            // Manejar la excepción
            e.printStackTrace();
        }
    }

    

    /*
    public ResponseEntity<String> stockProducto(Factura factura, Producto producto) {
        int stock = producto.getStock();
        //int compra = factura.getCantidadCompra();

        try {
            if (compra <= stock) {
                producto.setStock(stock - compra);
                String mensaje = "Cantidad: " + compra + " producto: " + producto.getDescripcion() + "\ncarga exitosa!";
                return ResponseEntity.status(200).body(mensaje);
            } else {
                throw new Exception("No hay suficiente stock para realizar la compra");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            String mensaje = "Stock insuficiente";
            return ResponseEntity.status(404).body(mensaje);
        }
    }


    public void precio(Factura factura, Producto producto, Venta venta) {
        ResponseEntity<String> response = stockProducto(factura, producto);

        if (response.getStatusCodeValue() == 200) {
            int compra = factura.getCantidadCompra();
            double precio = 0;

            for (int i = 1; i <= compra; i++) {
                precio += producto.getPrecio();
            }

            factura.setPrecioFinal(precio);
        }
    }

     */

    }

