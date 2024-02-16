//  Interfaz para interactuar con la base de datos y realizar operaciones en la tabla Cliente

package com.example.demo.repository;

import com.example.demo.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;


// Llamamos a la clase cliente y le decimos cual sera el ID : <Cliente, Long>
public interface RepositoryCliente extends JpaRepository<Cliente, Long>  {
}

// JpaRepository permite definir interfaces de repositorio sin necesidad de escribir ninguna implementación