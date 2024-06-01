package com.example.agencia_de_viagem.service;

import com.example.agencia_de_viagem.model.Destination;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

/**
 * Serviço responsável pela lógica de negócios relacionada a destinos.
 */
@Service
public class DestinationService {

    /**
     * Lista que simula um banco de dados para armazenar os destinos.
     */
    private List<Destination> destionations = new ArrayList<>();

    /**
     * Cria um novo destino e o adiciona à lista de destinos.
     *
     * @param destination O destino a ser criado.
     * @return O destino criado.
     */
    public Destination createDestionation(Destination destination) {
        destionations.add(destination);
        return destination;
    }

    /**
     * Retorna todos os destinos cadastrados.
     *
     * @return Uma lista com todos os destinos.
     */
    public List<Destination> getAllDestinations() {
        return this.destionations;
    }

    /**
     * Retorna o destino com o ID especificado.
     *
     * @param id O ID do destino a ser buscado.
     * @return O destino encontrado ou null se não encontrar nenhum.
     */
    public Destination getDestinationById(Long id) {
        Destination destination = destionations.stream().filter(destination1 -> destination1.getId() == id).findFirst().orElse(null);
        return destination;
    }

    /**
     * Retorna o destino com o nome especificado.
     *
     * @param name O nome do destino a ser buscado.
     * @return O destino encontrado.
     * @throws java.util.NoSuchElementException Se não encontrar nenhum destino com o nome especificado.
     */
    public Destination getDestinationByName(String name) {
        Destination destination = destionations.stream().filter(d -> d.getName().equals(name)).findFirst().get();
        return destination;
    }

    /**
     * Retorna o destino com a localização especificada.
     *
     * @param location A localização do destino a ser buscado.
     * @return O destino encontrado.
     * @throws java.util.NoSuchElementException Se não encontrar nenhum destino com a localização especificada.
     */
    public Destination getDestinationByLocation(String location) {
        Destination destination = destionations.stream().filter(d -> d.getLocation().equals(location)).findFirst().get();
        return destination;
    }

    /**
     * Retorna a descrição do destino com o ID especificado.
     *
     * @param id O ID do destino a ser buscado.
     * @return A descrição do destino ou uma mensagem padrão caso não encontre nenhuma descrição.
     */
    public String getDescriptionById(Long id) {
        String description = getDestinationById(id).getDescription();
        if (description == null) {
            return "Nenhuma descrição encontrada para o destino selecionado";
        }
        return description;
    }

    /**
     * Atualiza a avaliação de um destino.
     *
     * @param id O ID do destino a ser atualizado.
     * @param destination O objeto destino contendo a nova avaliação.
     * @return O destino com a avaliação atualizada.
     */
    public Destination updateRating(Long id, Destination destination) {
        Destination tempDestination = getDestinationById(id);
        double newRating = 0.0;
        if(tempDestination.getRating() == 0) {
            newRating = destination.getRating();
        }
        else {
            newRating = (tempDestination.getRating() + destination.getRating()) / 2;
        }
        tempDestination.setRating(newRating);
        return tempDestination;
    }

    /**
     * Remove um destino da lista pelo seu ID.
     *
     * @param id O ID do destino a ser removido.
     */
    public void deleteDestinationById(Long id) {
        destionations.removeIf(destination -> destination.getId() == id);
    }
}