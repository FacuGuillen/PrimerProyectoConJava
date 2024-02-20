package com.example.demo.repository;


import com.example.demo.models.Factura;
import org.springframework.data.jpa.repository.JpaRepository;



public interface RepositoryFactura extends JpaRepository<Factura, Long>  {
}

