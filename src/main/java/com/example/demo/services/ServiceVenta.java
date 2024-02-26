package com.example.demo.services;


import com.example.demo.models.Factura;
import com.example.demo.models.Producto;
import com.example.demo.models.Venta;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ServiceVenta {

    public ResponseEntity<String> stockProducto(Venta venta, Producto producto) {
        int stock = producto.getStock();
        int compra = venta.getCantidadCompraProducto();

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



        public void precio(Producto producto, Venta venta) {
            ResponseEntity<String> response = stockProducto(venta, producto);

            if (response.getStatusCodeValue() == 200) {
                int compra = venta.getCantidadCompraProducto();
                double precio = 0;

                for (int i = 1; i <= compra; i++) {
                    precio += producto.getPrecio();
                }

                venta.setPrecioPorProductos(precio);
            }
        }



}
