package com.example.agencia_de_viagem.controller;

import com.example.agencia_de_viagem.model.Destination;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/destination")
public class DestinationController {
    // Lista para armazenar os destinos (simulado por enquanto)
    List<Destination> destionations = new ArrayList<>();

    // Método para criar um novo destino
    @PostMapping
    public ResponseEntity<Destination> createDestination(@RequestBody Destination destination) {
        // Adiciona o novo destino na lista
        destionations.add(destination);
        // Retorna a resposta com o status de criação (CREATED) e o destino criado
        return new ResponseEntity<>(destination, HttpStatus.CREATED);
    }

    // Método para buscar todos os destinos
    @GetMapping
    public ResponseEntity<List<Destination>> getAllDestinations() {
        List<Destination> destinations = this.destionations;
        // Retorna a lista completa de destinos com o status OK
        return new ResponseEntity<>(destionations, HttpStatus.OK);
    }

    // Método para buscar um destino pelo identificador
    @GetMapping("/{id}")
    public ResponseEntity<Destination> getDestinationById(@PathVariable Long id) {
        // Busca o destino pelo id utilizando Stream API
        Destination destination = destionations.stream().filter(destination1 -> destination1.getId() == id).findFirst().orElse(null);
        // Retorna o destino encontrado com o status OK ou Not Found caso não seja encontrado
        return new ResponseEntity<>(destination, HttpStatus.OK);
    }

    // Método para buscar um destino pelo nome
    @GetMapping("/name/{name}")
    public ResponseEntity<Destination> getDestinationByName(@PathVariable String name) {
        // Busca o destino pelo nome utilizando Stream API
        Destination destination = destionations.stream().filter(d -> d.getName().equals(name)).findFirst().get();
        // Retorna o destino encontrado com o status OK
        return new ResponseEntity<>(destination, HttpStatus.OK);
    }

    // Método para buscar um destino pela localização
    @GetMapping("/location/{location}")
    public ResponseEntity<Destination> getDestinationByLocation(@PathVariable String location) {
        // Busca o destino pela localização utilizando Stream API
        Destination destination = destionations.stream().filter(d -> d.getLocation().equals(location)).findFirst().get();
        // Retorna o destino encontrado com o status OK
        return new ResponseEntity<>(destination, HttpStatus.OK);
    }

    // Método para buscar a descrição de um destino pelo identificador
    @GetMapping("/description/{id}")
    public ResponseEntity<String> getDescriptionById(@PathVariable Long id) {
        // Busca a descrição do destino pelo id utilizando Stream API
        String description = destionations.stream().filter(d -> d.getId() == id).findFirst().map(Destination::getDescription).orElse(null);
        // Verifica se a descrição foi encontrada
        if (description == null) {
            // Retorna Not Found caso não seja encontrada
            return ResponseEntity.notFound().build();
        }
        // Retorna a descrição encontrada com o status OK
        return ResponseEntity.ok(description);
    }

    // Método para atualizar a avaliação de um destino
    @PutMapping("/rating/{id}")
    public ResponseEntity<Destination> updateRating(@PathVariable Long id, @RequestBody Destination destination) {
        // Busca o destino pelo id utilizando Stream API
        Destination destination1 = destionations.stream().filter(d -> d.getId() == id).findFirst().get();
        // Calcula a nova média de avaliação (considerando a avaliação existente e a nova)
        double newRating = 0.0;
        if(destination1.getRating() == 0) {
            newRating = destination.getRating();
        }
        else {
            newRating = (destination1.getRating() + destination.getRating()) / 2;
        }
        // Atualiza a avaliação do destino
        destination1.setRating(newRating);
        // Retorna o destino atualizado com o status OK
        return new ResponseEntity<>(destination1, HttpStatus.OK);
    }

    // Método para excluir um destino pelo identificador
    @DeleteMapping("/{id}")
    public ResponseEntity<Destination> deleteDestinationById(@PathVariable Long id) {
        destionations.removeIf(destination -> destination.getId() == id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
