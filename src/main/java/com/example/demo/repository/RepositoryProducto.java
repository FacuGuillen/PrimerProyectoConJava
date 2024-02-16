package com.example.demo.repository;

import com.example.demo.models.Cliente;
import com.example.demo.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

// Llamamos a la clase cliente
public interface RepositoryProducto extends JpaRepository<Producto, Long>  {
}

// JpaRepository permite definir interfaces de repositorio sin necesidad de escribir ninguna implementación