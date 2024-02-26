// Endpoint

package com.example.demo.controllers;

import com.example.demo.models.Producto;
import com.example.demo.repository.RepositoryProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ControllerProducto {

    @Autowired
    private RepositoryProducto repo;


    // Tabla producto
    @GetMapping("productos")
    public List<Producto> getProducto(){
        return repo.findAll();
    }


    // Alta producto
    @PostMapping("altaproducto")
    public String post (@RequestBody Producto producto){
        repo.save(producto);
        String mensaje = "Producto guardado";
        return mensaje;
    }


    // Modificar datos de un producto
    @PutMapping("modificarproducto/{id}")
    public String update(@PathVariable Long id, @RequestBody Producto producto){
        Producto updateProducto = repo.findById(id).get();
        String mensaje = "El producto " + updateProducto.getDescripcion() + " ha sido modificado";
        updateProducto.setDescripcion(producto.getDescripcion());
        updateProducto.setPrecio(producto.getPrecio());
        repo.save(updateProducto);
        return mensaje;
    }


    // Eliminar producto
    @DeleteMapping("bajaproducto/{id}")
    public String delete(@PathVariable Long id){
        Producto deleteProducto = repo.findById(id).get();
        String mensaje = "El producto " + deleteProducto + "ha sido eliminado";
        repo.delete(deleteProducto);
        return mensaje;
    }
}
