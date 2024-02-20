package com.example.demo.controllers;

import com.example.demo.models.Cliente;
import com.example.demo.models.Factura;
import com.example.demo.repository.RepositoryFactura;
import com.example.demo.services.ServiceFactura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;


@RestController

public class ControllerFactura {

    @Autowired
    private RepositoryFactura repo;
    @Autowired
    private ServiceFactura serviceFactura;

    @GetMapping("facturas")
    public List getFacturas(){
        return repo.findAll();
    }

    @GetMapping("factura/{id}")
    public Factura getFactura(@PathVariable Long id){
        String mensajeError = "Factura n° " + id + " no encontrada";
        return repo.findById(id).orElseThrow(() -> new NoSuchElementException(mensajeError));
    }


    @PostMapping("altafactura")
    public String post (@RequestBody Factura factura){
        serviceFactura.FechaFactura(factura);
        repo.save(factura);
        String mensaje = "Factura n° " + factura.getId() + " guardada exitosamente";
        return  mensaje;
    }


    @PutMapping("modificarfactura/{id}")
    public String update(@PathVariable Long id, @RequestBody Factura factura){
        Factura updateFactura = repo.findById(id).get();
        String mensaje = "La factura n° " + updateFactura.getId() + " se ha modificado exitosamente";
        updateFactura.setCantidadCompra(factura.getCantidadCompra());
        updateFactura.setPrecioFinal(factura.getPrecioFinal());
        repo.save(updateFactura);
        return mensaje;
    }


    @DeleteMapping("notacredito/{id}")
    public String delete(@PathVariable Long id){
        Factura deteleFactura = repo.findById(id).get();
        String mensaje = "Nota de credito de la factura n° " + id;
        repo.delete(deteleFactura);
        return mensaje;
    }


}
