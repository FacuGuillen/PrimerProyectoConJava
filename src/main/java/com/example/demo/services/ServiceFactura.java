package com.example.demo.services;

import com.example.demo.models.Factura;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Service
public class ServiceFactura {

    public void FechaFactura(Factura factura) {
        RestTemplate restTemplate = new RestTemplate();
        String api = "http://worldclockapi.com/api/json/utc/now";
        ResponseEntity<String> response = restTemplate.getForEntity(api, String.class);

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode jsonNode = objectMapper.readTree(response.getBody());

            String currentDateTime = jsonNode.get("currentDateTime").asText();

            // Definir el formato de la fecha esperado
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mmX");

            // Parsear la fecha a LocalDateTime
            LocalDateTime fecha = LocalDateTime.parse(currentDateTime, formatter);

            factura.setFechaFactura(fecha);

        } catch (JsonProcessingException e) {
            // Manejar la excepción
            e.printStackTrace();
        }
    }

    }

