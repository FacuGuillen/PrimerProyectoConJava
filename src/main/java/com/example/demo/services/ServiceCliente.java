
package com.example.demo.services;

import com.example.demo.models.Cliente;
import com.example.demo.repository.RepositoryCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.Period;

@Service
public class ServiceCliente {

    public void calcularEdadCliente(Cliente cliente) {
        LocalDate fechaNacimiento = cliente.getFechaNacimiento();
        LocalDate fechaActual = LocalDate.now();
        int edad = fechaActual.getYear() - fechaNacimiento.getYear();

// Verificacion
        if (fechaActual.getDayOfYear() < fechaNacimiento.getDayOfYear()) {
            edad--;
        }

        cliente.setEdad(edad);
}}