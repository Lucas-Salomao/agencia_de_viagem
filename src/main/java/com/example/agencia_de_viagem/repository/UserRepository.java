package com.example.agencia_de_viagem.repository;

import com.example.agencia_de_viagem.domain.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UsersEntity, Long> {

}