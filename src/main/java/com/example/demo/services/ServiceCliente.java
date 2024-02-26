package com.example.demo.services;

import com.example.demo.models.Cliente;
import com.example.demo.repository.RepositoryCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.client.RestTemplate;

import java.security.PublicKey;
import java.time.LocalDate;
import java.time.Period;

@Service
public class ServiceCliente {

    @Autowired
    private RepositoryCliente repo;

    public void calcularEdadCliente(Cliente cliente) {
        LocalDate fechaNacimiento = cliente.getFechaNacimiento();
        LocalDate fechaActual = LocalDate.now();
        int edad = fechaActual.getYear() - fechaNacimiento.getYear();

// Verificacion
        if (fechaActual.getDayOfYear() < fechaNacimiento.getDayOfYear()) {
            edad--;
        }

        cliente.setEdad(edad);
}

    public String guardarCliente(Cliente cliente) {
        calcularEdadCliente(cliente);
        repo.save(cliente);
        String mensaje = "El cliente " + cliente.getNombre() + " ha sido guardado exitosamente";
        return mensaje;
    }


    public String updateCliente(Long id, Cliente cliente) {
        Cliente updateCliente = repo.findById(id).get();
        updateCliente.setNombre(cliente.getNombre());
        updateCliente.setEmail(cliente.getEmail());
        updateCliente.setFechaNacimiento(cliente.getFechaNacimiento());
        repo.save(updateCliente);
        String mensaje = "El cliente con el nombre " + cliente.getNombre() + " ha sido modificado";
        return mensaje;
    }

    public String deleteCliente(Long id) {
        Cliente deleteCliente = repo.findById(id).get(); // Busca en repo el Cliente con el id ingresado para luego asignarselo a deleteCliente
        String mensaje = "El cliente " + deleteCliente + "ha sido eliminado";
        repo.delete(deleteCliente); // Elimina
        return mensaje;
    }
}