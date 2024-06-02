package com.example.agencia_de_viagem.service;

import com.example.agencia_de_viagem.dao.DestinationDAO;
import com.example.agencia_de_viagem.domain.dto.DestinationDTO;
import com.example.agencia_de_viagem.domain.entity.DestinationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Serviço responsável pela lógica de negócios relacionada a destinos.
 */
@Service
public class DestinationService {

    @Autowired
    private DestinationDAO destinationDAO;

    /**
     * Converte um DestinationDTO para um DestinationEntity.
     *
     * @param destinationDTO O DTO a ser convertido.
     * @return A entidade correspondente.
     */
    private DestinationEntity toEntity(DestinationDTO destinationDTO) {
        DestinationEntity destinationEntity = new DestinationEntity();
        destinationEntity.setName(destinationDTO.getName());
        destinationEntity.setLocation(destinationDTO.getLocation());
        destinationEntity.setDescription(destinationDTO.getDescription());
        destinationEntity.setRating(destinationDTO.getRating());
        return destinationEntity;
    }

    /**
     * Converte um DestinationEntity para um DestinationDTO.
     *
     * @param destinationEntity A entidade a ser convertida.
     * @return O DTO correspondente.
     */
    private DestinationDTO toDTO(DestinationEntity destinationEntity) {
        DestinationDTO destinationDTO = new DestinationDTO();
        destinationDTO.setId(destinationEntity.getId());
        destinationDTO.setName(destinationEntity.getName());
        destinationDTO.setLocation(destinationEntity.getLocation());
        destinationDTO.setDescription(destinationEntity.getDescription());
        destinationDTO.setRating(destinationEntity.getRating());
        return destinationDTO;
    }

    /**
     * Cria um novo destino e o adiciona ao banco de dados.
     *
     * @param destination O destino a ser criado.
     * @return O destino criado.
     */
    public DestinationDTO createDestionation(DestinationDTO destination) {
        DestinationEntity destinationEntity = toEntity(destination); // Converte DTO para Entity
        destinationEntity = destinationDAO.createDestination(destinationEntity);
        return toDTO(destinationEntity); // Converte Entity para DTO
    }

    /**
     * Retorna todos os destinos cadastrados.
     *
     * @return Uma lista com todos os destinos.
     */
    public List<DestinationDTO> getAllDestinations() {
        List<DestinationEntity> destinationEntities = destinationDAO.getAllDestinations();
        return destinationEntities.stream()
                .map(this::toDTO) // Utiliza a função toDTO para converter a lista de entidades para DTOs
                .collect(Collectors.toList());
    }

    /**
     * Retorna o destino com o ID especificado.
     *
     * @param id O ID do destino a ser buscado.
     * @return O destino encontrado ou null se não encontrar nenhum.
     */
    public DestinationDTO getDestinationById(Long id) {
        DestinationEntity destinationEntity = destinationDAO.getDestinationById(id);
        if (destinationEntity == null) {
            return null;
        }
        return toDTO(destinationEntity); // Converte a entidade para DTO
    }

    /**
     * Retorna o destino com o nome especificado.
     *
     * @param name O nome do destino a ser buscado.
     * @return O destino encontrado.
     */
    public DestinationDTO getDestinationByName(String name) {
        DestinationEntity destinationEntity = destinationDAO.getDestinationByName(name);
        if (destinationEntity == null) {
            return null;
        }
        return toDTO(destinationEntity); // Converte a entidade para DTO
    }

    /**
     * Retorna o destino com a localização especificada.
     *
     * @param location A localização do destino a ser buscado.
     * @return O destino encontrado.
     */
    public List<DestinationDTO> getDestinationByLocation(String location) {
        // O DAO não possui um método para buscar por localização.
        // Implementar uma consulta no DAO para buscar por localização.
        List<DestinationEntity> destinationEntities = destinationDAO.getAllDestinations();
        return destinationEntities.stream()
                .filter(destinationEntity -> destinationEntity.getLocation().equals(location))
                .map(this::toDTO) // Utiliza a função toDTO para converter a lista de entidades para DTOs
                .collect(Collectors.toList());
    }

    /**
     * Retorna a descrição do destino com o ID especificado.
     *
     * @param id O ID do destino a ser buscado.
     * @return A descrição do destino ou uma mensagem padrão caso não encontre nenhuma descrição.
     */
    public String getDescriptionById(Long id) {
        String description = destinationDAO.getDescriptionById(id);
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
    public DestinationDTO updateRating(Long id, DestinationDTO destination) {
        DestinationEntity destinationEntity = destinationDAO.getDestinationById(id);
        if (destinationEntity == null) {
            return null;
        }
        double newRating = (destinationEntity.getRating() + destination.getRating()) / 2;
        destinationEntity.setRating(newRating);
        destinationEntity = destinationDAO.updateRating(destinationEntity);
        return toDTO(destinationEntity); // Converte a entidade para DTO
    }

    /**
     * Remove um destino da lista pelo seu ID.
     *
     * @param id O ID do destino a ser removido.
     */
    public void deleteDestinationById(Long id) {
        destinationDAO.deleteDestination(id);
    }
}