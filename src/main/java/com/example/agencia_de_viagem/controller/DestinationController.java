package com.example.agencia_de_viagem.controller;

import com.example.agencia_de_viagem.model.Destination;
import com.example.agencia_de_viagem.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para gerenciar destinos turísticos.
 */
@RestController
@RequestMapping("/api/v1/destination") // Define o endpoint base para este controlador
public class DestinationController {

    // Injeta o serviço responsável pela lógica de negócios relacionada aos destinos.
    @Autowired
    private DestinationService destinationService;

    /**
     * Cria um novo destino.
     *
     * @param destination O objeto Destination contendo as informações do novo destino.
     * @return ResponseEntity com o status HTTP 201 (Created) e o destino criado.
     */
    @PostMapping
    public ResponseEntity<Destination> createDestination(@RequestBody Destination destination) {
        // Delega a criação do destino para o serviço.
        destinationService.createDestionation(destination);
        // Retorna o destino criado com o status HTTP 201 (Created).
        return new ResponseEntity<>(destination, HttpStatus.CREATED);
    }

    /**
     * Retorna todos os destinos.
     *
     * @return ResponseEntity com o status HTTP 200 (OK) e a lista de destinos.
     */
    @GetMapping
    public ResponseEntity<List<Destination>> getAllDestinations() {
        // Busca todos os destinos usando o serviço.
        List<Destination> destinations = destinationService.getAllDestinations();
        // Retorna a lista de destinos com o status HTTP 200 (OK).
        return new ResponseEntity<>(destinations, HttpStatus.OK);
    }

    /**
     * Retorna o destino com o ID especificado.
     *
     * @param id O ID do destino a ser buscado.
     * @return ResponseEntity com o status HTTP 200 (OK) e o destino encontrado ou status 404 (Not Found) caso não seja encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Destination> getDestinationById(@PathVariable Long id) {
        // Busca o destino pelo ID usando o serviço.
        Destination destination = destinationService.getDestinationById(id);
        // Retorna o destino com o status HTTP 200 (OK) ou status 404 (Not Found) caso não seja encontrado.
        return new ResponseEntity<>(destination, HttpStatus.OK);
    }

    /**
     * Retorna o destino com o nome especificado.
     *
     * @param name O nome do destino a ser buscado.
     * @return ResponseEntity com o status HTTP 200 (OK) e o destino encontrado.
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<Destination> getDestinationByName(@PathVariable String name) {
        // Busca o destino pelo nome usando o serviço.
        Destination destination = destinationService.getDestinationByName(name);
        // Retorna o destino com o status HTTP 200 (OK).
        return new ResponseEntity<>(destination, HttpStatus.OK);
    }

    /**
     * Retorna o destino com a localização especificada.
     *
     * @param location A localização do destino a ser buscado.
     * @return ResponseEntity com o status HTTP 200 (OK) e o destino encontrado.
     */
    @GetMapping("/location/{location}")
    public ResponseEntity<Destination> getDestinationByLocation(@PathVariable String location) {
        // Busca o destino pela localização usando o serviço.
        Destination destination = destinationService.getDestinationByLocation(location);
        // Retorna o destino com o status HTTP 200 (OK).
        return new ResponseEntity<>(destination, HttpStatus.OK);
    }

    /**
     * Retorna a descrição do destino com o ID especificado.
     *
     * @param id O ID do destino.
     * @return ResponseEntity com o status HTTP 200 (OK) e a descrição do destino.
     */
    @GetMapping("/description/{id}")
    public ResponseEntity<String> getDescriptionById(@PathVariable Long id) {
        // Busca a descrição do destino pelo ID usando o serviço.
        String description = destinationService.getDescriptionById(id);
        // Retorna a descrição com o status HTTP 200 (OK).
        return ResponseEntity.ok(description);
    }

    /**
     * Atualiza a avaliação de um destino.
     *
     * @param id O ID do destino a ser atualizado.
     * @param destination O objeto Destination contendo a nova avaliação.
     * @return ResponseEntity com o status HTTP 200 (OK) e o destino atualizado.
     */
    @PutMapping("/rating/{id}")
    public ResponseEntity<Destination> updateRating(@PathVariable Long id, @RequestBody Destination destination) {
        // Atualiza a avaliação do destino usando o serviço.
        Destination tempDestination = destinationService.updateRating(id, destination);
        // Retorna o destino atualizado com o status HTTP 200 (OK).
        return new ResponseEntity<>(tempDestination, HttpStatus.OK);
    }

    /**
     * Exclui um destino.
     *
     * @param id O ID do destino a ser excluído.
     * @return ResponseEntity com o status HTTP 204 (No Content).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Destination> deleteDestinationById(@PathVariable Long id) {
        // Exclui o destino usando o serviço.
        destinationService.deleteDestinationById(id);
        // Retorna o status HTTP 204 (No Content) indicando que a exclusão foi realizada com sucesso.
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}