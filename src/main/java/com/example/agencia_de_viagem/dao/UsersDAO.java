package com.example.agencia_de_viagem.dao;

import com.example.agencia_de_viagem.domain.entity.UserEntity;
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

    public UserEntity createUser(UserEntity userEntity) {
        entityManager.getTransaction().begin();
        entityManager.persist(userEntity);
        entityManager.getTransaction().commit();
        return userEntity;
    }

    public List<UserEntity> getAllUsers() {
        return entityManager.createQuery("SELECT u from UserEntity u", UserEntity.class).getResultList();
    }

    public UserEntity getUserById(Long id) {
        return entityManager.find(UserEntity.class, id);
    }

    public UserEntity getUserByUserName(String userName) {
        return entityManager.find(UserEntity.class, userName); // userName should be unique
    }

    public UserEntity updateUser(UserEntity userEntity) {
        entityManager.getTransaction().begin();
        entityManager.merge(userEntity);
        entityManager.getTransaction().commit();
        return userEntity;
    }

    public void deleteUser(Long id) {
        entityManager.getTransaction().begin();
        UserEntity userEntity = entityManager.find(UserEntity.class, id);
        if (userEntity != null) {
            entityManager.remove(userEntity);
        }
        entityManager.getTransaction().commit();
    }
}
