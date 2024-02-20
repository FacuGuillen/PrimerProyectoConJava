package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("conectado")
    public String index(){
        String mensaje = "Conexión establecida";
        return mensaje;
    }
}
