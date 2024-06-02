package com.example.agencia_de_viagem.dao;

import com.example.agencia_de_viagem.domain.entity.UsersEntity;
import org.springframework.stereotype.Component;
import jakarta.persistence.*;

import java.util.List;

@Component
public class UsersDAO {

    private EntityManager entityManager;

    private EntityManagerFactory entityManagerFactory;

    public UsersDAO() {
        entityManagerFactory = Persistence.createEntityManagerFactory("agencia_de_viagem");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public UsersEntity createUser(UsersEntity userEntity) {
        entityManager.getTransaction().begin();
        entityManager.persist(userEntity);
        entityManager.getTransaction().commit();
        return userEntity;
    }

    public List<UsersEntity> getAllUsers() {
        return entityManager.createQuery("SELECT u from UsersEntity u", UsersEntity.class).getResultList();
    }

    public UsersEntity getUserById(Long id) {
        return entityManager.find(UsersEntity.class, id);
    }

    public UsersEntity getUserByUserName(String userName) {
        return entityManager.find(UsersEntity.class, userName); // userName should be unique
    }

    public UsersEntity updateUser(UsersEntity userEntity) {
        entityManager.getTransaction().begin();
        entityManager.merge(userEntity);
        entityManager.getTransaction().commit();
        return userEntity;
    }

    public void deleteUser(Long id) {
        entityManager.getTransaction().begin();
        UsersEntity userEntity = entityManager.find(UsersEntity.class, id);
        if (userEntity != null) {
            entityManager.remove(userEntity);
        }
        entityManager.getTransaction().commit();
    }
}
