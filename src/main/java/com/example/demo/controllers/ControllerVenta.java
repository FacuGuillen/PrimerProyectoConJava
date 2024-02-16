// Endpoint

package com.example.demo.controllers;

import com.example.demo.models.Venta;
import com.example.demo.repository.RepositoryVenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ControllerVenta {

    @Autowired
    private RepositoryVenta repo;


    // Tabla venta
    @GetMapping("ventas")
    public List<Venta> getVentas(){
        return repo.findAll();
    }


    // Dar de alta una venta
    @PostMapping("altaventa")
    public String post (@RequestBody Venta venta){
        repo.save(venta);
        return "Venta guardada";
    }


    // Modificar datos de un cliente
    @PutMapping("modificarventa/{id}")
    public String update(@PathVariable Long id, @RequestBody Venta venta){
        Venta updateVenta = repo.findById(id).get();
        String mensaje = "La venta ha sido modificado";
        updateVenta.setNombreVendedor(venta.getNombreVendedor());
        updateVenta.setCuitComprador(venta.getCuitComprador());
        repo.save(updateVenta);
        return mensaje;
    }


    // Eliminar venta
    @DeleteMapping("bajaventa/{id}")
    public String delete(@PathVariable Long id){
        Venta deleteVenta = repo.findById(id).get();
        String mensaje = "La venta nro: " + deleteVenta.getId() + " ha sido eliminado";
        repo.delete(deleteVenta);
        return mensaje;
    }

}
