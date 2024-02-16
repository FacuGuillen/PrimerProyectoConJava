package com.example.demo.repository;

import com.example.demo.models.Venta;
import org.springframework.data.jpa.repository.JpaRepository;


// Llamamos a la clase venta
public interface RepositoryVenta extends JpaRepository<Venta, Long> {
}

// JpaRepository permite definir interfaces de repositorio sin necesidad de escribir ninguna implementación
