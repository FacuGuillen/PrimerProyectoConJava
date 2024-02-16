// Endpoint

package com.example.demo.controllers;

import com.example.demo.models.Cliente;
import com.example.demo.repository.RepositoryCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import com.example.demo.services.ServiceCliente;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;


@RestController // Indica que esta clase será una controladora en una app web y será responsable de manejar las solicitudes HTTP (Get, Put...)

public class ControllerCliente {

    int contFecha = 0;

    @Autowired // Inyeccion de dependencia 
    private RepositoryCliente repo;
    @Autowired
    private ServiceCliente serviceCliente;


    // Conectado a la base de datos
    @GetMapping
    public String index(){
        return "Conectado";
    }


    // Tabla clientes
    @GetMapping("clientes") // @GetMapping: La solicitud que voy a estar realizando mediante el endpoint sera una solicitud a un metodo Get
    public List getClientes(){ // Lista de objetos clientes llamada Clientes
        return repo.findAll(); // Este método busca y devuelve todos los registros de una entidad en la base de datos.
    }


    @GetMapping("cliente/{id}")
    public Cliente getCliente(@PathVariable Long id){
        return repo.findById(id).orElseThrow(() -> new NoSuchElementException("Cliente no encontrado"));
    }



    @GetMapping("fecha")
    public List getFechaActual(){
        contFecha++;
        LocalDate fechaActual = LocalDate.now();
        return Collections.singletonList(fechaActual);
    }


    @GetMapping("contfecha")
    public int getContFecha(){
        return contFecha;
    }


    // Dar de alta a un cliente
    @PostMapping("alta") // @PostMapping: La solicitud que voy a estar realizando mediante el endpoint sera una solicitud a un metodo Post
    public String post (@RequestBody Cliente cliente){ // Trae la informacion desde el body de Cliente y la guarda en un JSON llamado cliente
        serviceCliente.calcularEdadCliente(cliente);
        repo.save(cliente); // Guarda un objeto Cliente
        String mensaje = "El cliente " + cliente.getNombre() + " ha sido guardado exitosamente";
        return mensaje;
    }

    // Modificar datos de un cliente
    @PutMapping("modificar/{id}") // @PutMapping: La solicitud que voy a estar realizando mediante el endpoint sera una solicitud de un metodo Put
    // modificar/{id} en un endpoint
    public String update(@PathVariable Long id, @RequestBody Cliente cliente){ // Recibe parametro ID y parametro cliente
        Cliente updateCliente = repo.findById(id).get(); // Guarda el objeto cliente con el id ingresado en updateCliente
        String mensaje = "El cliente " + updateCliente.getNombre() + " ha sido modificado";
        updateCliente.setNombre(cliente.getNombre()); // Se modifican los atributos del objeto updateCliente con los valores del objeto cliente recibido
        updateCliente.setEmail(cliente.getEmail());
        updateCliente.setFechaNacimiento(cliente.getFechaNacimiento());
        repo.save(updateCliente); // Guarda valores de updateCliente y los guarda en el objeto cliente id
        return mensaje;
    }

    // Eliminar cliente
    @DeleteMapping("baja/{id}") // @DeleteMapping: La solicitud que voy a estar realizando mediante el endpoint sera una solicitud de un metodo Delete
    public String delete(@PathVariable Long id){
        Cliente deleteCliente = repo.findById(id).get(); // Busca en repo el Cliente con el id ingresado para luego asignarselo a deleteCliente
        String mensaje = "El cliente " + deleteCliente + "ha sido eliminado";
        repo.delete(deleteCliente); // Elimina
        return mensaje;
    }




}

