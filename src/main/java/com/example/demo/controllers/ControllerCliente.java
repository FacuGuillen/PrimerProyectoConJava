// Endpoint

package com.example.demo.controllers;

import com.example.demo.models.Cliente;
import com.example.demo.repository.RepositoryCliente;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import com.example.demo.services.ServiceCliente;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;


@RestController // Indica que esta clase será una controladora en una app web y será responsable de manejar las solicitudes HTTP (Get, Put...)

public class ControllerCliente {

    @Autowired // Inyeccion de dependencia 
    private RepositoryCliente repo;
    @Autowired
    private ServiceCliente serviceCliente;


    // Tabla clientes
    @Operation(summary = "List client", description = "Lista todos los clientes")
    @GetMapping("clientes") // @GetMapping: La solicitud que voy a estar realizando mediante el endpoint sera una solicitud a un metodo Get
    public List getClientes(){ // Lista de objetos clientes llamada Clientes
        return repo.findAll(); // Este método busca y devuelve todos los registros de una entidad en la base de datos.
    }


    @Operation (summary = "Print client by id", description = "Mostrar cliente por su id")
    @GetMapping("cliente/{id}")
    public Cliente getCliente(@PathVariable Long id){
        return repo.findById(id).orElseThrow(() -> new NoSuchElementException("Cliente no encontrado"));
    }


    // Dar de alta a un cliente
    @Operation (summary = "Create new client", description = "Agrega cliente a la DB")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "No se pudo realizar la operacion"
                    ,content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Cliente.class))})})
    @PostMapping("alta") // @PostMapping: La solicitud que voy a estar realizando mediante el endpoint sera una solicitud a un metodo Post
    public String post (@RequestBody Cliente cliente){ // Trae la informacion desde el body de Cliente y la guarda en un JSON llamado cliente
        return serviceCliente.guardarCliente(cliente);
    }

    // Modificar datos de un cliente
    @Operation(summary = "Modify customer data", description = "Modifica los datos del cliente seleccionado por su id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "No se pudo realizar la operacion "
                    ,content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Cliente.class))})})
    @PutMapping("modificar/{id}") // @PutMapping: La solicitud que voy a estar realizando mediante el endpoint sera una solicitud de un metodo Put
    // modificar/{id} en un endpoint
    public String update(@PathVariable Long id, @RequestBody Cliente cliente){ // Recibe parametro ID y parametro cliente
        return serviceCliente.updateCliente(id, cliente);
    }

    // Eliminar cliente
    @Operation(summary = "Delete client", description = "Elimina el cliente seleccionado por su id")
    @DeleteMapping("baja/{id}") // @DeleteMapping: La solicitud que voy a estar realizando mediante el endpoint sera una solicitud de un metodo Delete
    public String delete(@PathVariable Long id){
        return serviceCliente.deleteCliente(id);
    }

}

