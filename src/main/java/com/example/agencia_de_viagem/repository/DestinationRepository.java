package com.example.agencia_de_viagem.repository;

import com.example.agencia_de_viagem.domain.entity.DestinationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationRepository extends JpaRepository<DestinationEntity, Long> {

    String findDescriptionById(Long id);

    DestinationEntity findDestinationByName(String name);
}