package com.example.agencia_de_viagem.dao;

import com.example.agencia_de_viagem.domain.entity.DestinationEntity;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Component
public class DestinationDAO {

    private EntityManager entityManager;

    private EntityManagerFactory entityManagerFactory;

    public DestinationDAO() {
        entityManagerFactory=Persistence.createEntityManagerFactory("agencia_de_viagem");
        entityManager=entityManagerFactory.createEntityManager();
    }

    public DestinationEntity createDestination(DestinationEntity destinationEntity){
        entityManager.getTransaction().begin();
        entityManager.persist(destinationEntity);
        entityManager.getTransaction().commit();
        return destinationEntity;
    }

    public List<DestinationEntity> getAllDestinations() {
        return entityManager.createQuery("SELECT u from DestinationEntity u", DestinationEntity.class).getResultList();
    }

    public DestinationEntity getDestinationById(Long id) {
        return entityManager.find(DestinationEntity.class, id);
    }

    public DestinationEntity getDestinationByName(String name) {
        return entityManager.find(DestinationEntity.class, name);
    }

    public DestinationEntity getDestinationByLocation(String location) {
        return entityManager.find(DestinationEntity.class, location);
    }

    public String getDescriptionById(Long id){
        return entityManager.find(DestinationEntity.class, id).getDescription();
    }

    public DestinationEntity updateRating(DestinationEntity destinationEntity){
        entityManager.getTransaction().begin();
        entityManager.merge(destinationEntity);
        entityManager.getTransaction().commit();
        return destinationEntity;
    }

    public void deleteDestination(Long id){
        entityManager.getTransaction().begin();
        DestinationEntity destinationEntity=entityManager.find(DestinationEntity.class,id);
        if(destinationEntity!=null) {
            entityManager.remove(destinationEntity);
        }
        entityManager.getTransaction().commit();
        return;
    }
}
